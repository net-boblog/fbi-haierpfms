package pfms.cron;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pfms.enums.EnuZzsSrc;
import pfms.repository.model.InvZzsSyncFailKey;
import pfms.repository.model.custom.ReqGuojie;
import pfms.repository.model.custom.ResGuojie;
import pfms.service.inv.InvZzsSrcService;
import pfms.service.inv.InvZzsSyncFailService;
import pfms.util.HttpClientUtil;
import pfms.util.ToolUtil;
import skyline.common.PropertyManager;

import javax.annotation.Resource;
import java.util.List;

/**
 * �ӹ���ȡ����ֵ˰���ݲ�����ԭʼ���ݱ�
 */
@Component
public class ZzsGuojieTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsSrcService invZzsSrcService;

    @Resource
    private InvZzsSyncFailService syncFailService;

    public void runTask() throws JobExecutionException {
        String txnDate = "";
        String datasrc = EnuZzsSrc.SRC_01.getCode();
        try {
            logger.info("----------[�ӹ���ȡ����ֵ˰����]��ʼ��" + ToolUtil.getDateTimeDashColon() + "----------");
            txnDate = new DateTime().plusDays(-1).toString("yyyy-MM-dd");
            String url = PropertyManager.getProperty("HTTP_URL_GUOJIE");
            List<InvZzsSyncFailKey> syncFailList = syncFailService.select(txnDate, datasrc);
            InvZzsSyncFailKey syncFailKey = new InvZzsSyncFailKey();
            syncFailKey.setTxnDate(txnDate);
            syncFailList.add(syncFailKey);
            boolean isSuccess = false;
            for (InvZzsSyncFailKey record : syncFailList) {
                txnDate = record.getTxnDate();
                isSuccess = syncGuojie(url, txnDate);
                if (isSuccess) {
                    syncFailService.delete(txnDate, datasrc);
                } else {
                    syncFailService.insert(txnDate, datasrc);
                }
            }
            logger.info("----------[�ӹ���ȡ����ֵ˰����]������" + ToolUtil.getDateTimeDashColon() + "----------");
        } catch (Exception e) {
            if (StringUtils.isNotEmpty(txnDate)) {
                syncFailService.insert(txnDate, datasrc);
            }
            logger.error("�ӹ���ȡ����ֵ˰����ʧ�ܣ�", e);
            throw new JobExecutionException("�ӹ���ȡ����ֵ˰����ʧ�ܣ�" + e == null ? "" : e.getMessage(), e);
        }
    }

    /**
     * �ӹ���ȡ����ֵ˰����
     *
     * @param url     ����HTTP�ӿڵ�ַ
     * @param txnDate ��������
     * @return
     * @throws Exception
     */
    private boolean syncGuojie(String url, String txnDate) throws Exception {
        String requestXml = getRequestXml(txnDate.replace("-", "")); // ȡ��������
        logger.info("----------[�ӹ���ȡ����ֵ˰����]�����ģ�" + requestXml + "----------");
        byte[] response = HttpClientUtil.doPost(url, requestXml);
        String responseXml = new String(response, "GBK");
        logger.info("----------[�ӹ���ȡ����ֵ˰����]��Ӧ���ģ�" + responseXml + "----------");
        ResGuojie resGuojie = ToolUtil.xmlToBean(responseXml, ResGuojie.class);
        if (resGuojie != null && ToolUtil.RET_CODE_SUCCESS.equals(resGuojie.getResHeader().getRet_code())) {
            int totalCount = 0; // �ܼ�¼��
            if (StringUtils.isNotEmpty(resGuojie.getResBody().getTOTALCOUNT())) {
                totalCount = Integer.valueOf(resGuojie.getResBody().getTOTALCOUNT());
            }
            if (totalCount > 0) {
                invZzsSrcService.insertByGuojie(txnDate, resGuojie.getResBody().getResContent().getBeanList());
            }
            if (ToolUtil.RESULT_SUCCESS.equals(resGuojie.getResBody().getRESULT())) {
                return true;
            }
        }
        return false;
    }

    /**
     * ȡ��������
     *
     * @param txnDate
     * @return
     */
    private String getRequestXml(String txnDate) {
        ReqGuojie reqGuojie = new ReqGuojie();

        // ����������ͷ
        ReqGuojie.ReqHeader reqHeader = new ReqGuojie.ReqHeader();
        reqHeader.setRequest_date(ToolUtil.getDate());           // ��������
        reqHeader.setRequest_time(ToolUtil.getTime());           // ����ʱ��
        reqHeader.setRequest_seq_no(ToolUtil.getDateTimeMsec()); // ������ˮ��
        reqGuojie.setReqHeader(reqHeader);

        // ������������
        ReqGuojie.ReqBody reqBody = new ReqGuojie.ReqBody();
        reqBody.setTrade_date(txnDate); // ��������
        reqGuojie.setReqBody(reqBody);

        return ToolUtil.beanToXml(reqGuojie);
    }

    //==============================================get set=====================================================
    public InvZzsSrcService getInvZzsSrcService() {
        return invZzsSrcService;
    }

    public void setInvZzsSrcService(InvZzsSrcService invZzsSrcService) {
        this.invZzsSrcService = invZzsSrcService;
    }

    public InvZzsSyncFailService getSyncFailService() {
        return syncFailService;
    }

    public void setSyncFailService(InvZzsSyncFailService syncFailService) {
        this.syncFailService = syncFailService;
    }
}
