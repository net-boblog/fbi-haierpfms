package pfms.cron;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pfms.repository.model.custom.ReqGuojie;
import pfms.repository.model.custom.ResGuojie;
import pfms.service.inv.InvZzsSrcService;
import pfms.util.HttpClientUtil;
import pfms.util.ToolUtil;
import skyline.common.PropertyManager;

import javax.annotation.Resource;

/**
 * �ӹ���ȡ����ֵ˰���ݲ�����ԭʼ���ݱ�
 */
@Component
public class ZzsGuojieTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsSrcService invZzsSrcService;

    public void runTask() throws JobExecutionException {
        try {
            logger.info("----------[�ӹ���ȡ����ֵ˰����]��ʼ��" + ToolUtil.getDateTimeDashColon() + "----------");
            String http_url_guojie = PropertyManager.getProperty("HTTP_URL_GUOJIE");
            String txnDate = new DateTime().plusDays(-1).toString("yyyy-MM-dd");
            txnDate = "2016-04-25";
            String requestXml = getRequestXml(txnDate.replace("-", "")); // ȡ��������
            byte[] response = HttpClientUtil.doPost(http_url_guojie, requestXml);
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
            }
            logger.info("----------[�ӹ���ȡ����ֵ˰����]������" + ToolUtil.getDateTimeDashColon() + "----------");
        } catch (Exception e) {
            logger.error("�ӹ���ȡ����ֵ˰����ʧ�ܣ�", e);
            throw new JobExecutionException("�ӹ���ȡ����ֵ˰����ʧ�ܣ�" + e == null ? "" : e.getMessage(), e);
        }
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
}
