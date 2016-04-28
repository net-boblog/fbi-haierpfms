package pfms.cron;

import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T467;
import gateway.sbs.txn.model.msg.Mn073;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pfms.enums.EnuZzsSrc;
import pfms.repository.model.InvZzsSyncFailKey;
import pfms.service.DataExchangeService;
import pfms.service.inv.InvZzsSrcService;
import pfms.service.inv.InvZzsSyncFailService;
import pfms.util.ToolUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ��SBSȡ����ֵ˰���ݲ�����ԭʼ���ݱ�
 */
@Component
public class ZzsSbsTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String operId = "MPC1";                        // SBS��Ա

    @Resource
    private InvZzsSrcService invZzsSrcService;

    @Resource
    private InvZzsSyncFailService syncFailService;

    @Resource
    private DataExchangeService dataExchangeService;

    public void runTask() throws JobExecutionException {
        String txnDate = "";
        String datasrc = EnuZzsSrc.SRC_00.getCode();
        try {
            logger.info("----------[��SBSȡ����ֵ˰����]��ʼ��" + ToolUtil.getDateTimeDashColon() + "----------");
            txnDate = new DateTime().plusDays(-1).toString("yyyy-MM-dd");
            List<InvZzsSyncFailKey> syncFailList = syncFailService.select(txnDate, datasrc);
            InvZzsSyncFailKey syncFailKey = new InvZzsSyncFailKey();
            syncFailKey.setTxnDate(txnDate);
            syncFailList.add(syncFailKey);
            boolean isSuccess = false;
            for (InvZzsSyncFailKey record : syncFailList) {
                txnDate = record.getTxnDate();
                isSuccess = syncSBS(txnDate);
                if (isSuccess) {
                    syncFailService.delete(txnDate, datasrc);
                } else {
                    syncFailService.insert(txnDate, datasrc);
                }
            }
            logger.info("----------[��SBSȡ����ֵ˰����]������" + ToolUtil.getDateTimeDashColon() + "----------");
        } catch (Exception e) {
            if (StringUtils.isNotEmpty(txnDate)) {
                syncFailService.insert(txnDate, datasrc);
            }
            logger.error("��SBSȡ����ֵ˰����ʧ�ܣ�", e);
            throw new JobExecutionException("��SBSȡ����ֵ˰����ʧ�ܣ�" + e == null ? "" : e.getMessage(), e);
        }
    }

    /**
     * ��SBSȡ����ֵ˰����
     *
     * @param txnDate
     * @return
     * @throws Exception
     */
    public boolean syncSBS(String txnDate) throws Exception {
        boolean result = true;
        String totcnt = "";
        String curcnt = "";
        int m = 0;//ȡ��
        int n = 0;//ȡ��
        Mn073 mn073 = new Mn073();
        mn073.setORDDAT(txnDate.replace("-", "")); // ����
        mn073.setORDNUM("");      // ������
        mn073.setANACDE("");      // �������
        mn073.setBEGNUM("1");     // ��ʼ����
        List<SOFForm> formList = dataExchangeService.callSbsTxn("n073", mn073, operId);
        List<T467.Bean> dataList = new ArrayList<T467.Bean>();
        T467 t467 = null;
        if (formList != null && !formList.isEmpty()) {
            for (SOFForm form : formList) {
                if ("T467".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                    t467 = (T467) form.getFormBody();
                    totcnt = t467.getTotcnt();
                    curcnt = t467.getCurcnt();
                    dataList = t467.getBeanList();
                } else if("W107".equalsIgnoreCase(form.getFormHeader().getFormCode())){
                    logger.info("���ڣ�" + txnDate + "û����ֵ˰����");
                } else {
                    result = false;
                    logger.error(form.getFormHeader().getFormCode());
                }
            }
        }
        if (!totcnt.isEmpty() && totcnt != "") {
            //��Ϊ totcnt��ȫ�ֱ����������ڵ�һ�β�ѯ֮�󣬷���ڶ��ν���ʱtotcnt�Ͳ�Ϊ�գ�����Ҫ�ڵ�һ�η�����ʱ���
            m = Integer.parseInt(totcnt) / Integer.parseInt(curcnt);
            n = Integer.parseInt(totcnt) % Integer.parseInt(curcnt);
            if (n != 0) {
                m = m + 1;
            }
            String tmp = "";
            for (int j = 1; j < m; j++) {
                tmp = j * Integer.parseInt(curcnt) + 1 + "";
                mn073.setBEGNUM(tmp);
                List<SOFForm> formList2 = dataExchangeService.callSbsTxn("n073", mn073, operId);
                if (formList2 != null && !formList2.isEmpty()) {
                    for (SOFForm form : formList2) {
                        if ("T467".equalsIgnoreCase(form.getFormHeader().getFormCode())) {
                            t467 = (T467) form.getFormBody();
                            dataList.addAll(t467.getBeanList());
                        } else {
                            result = false;
                            logger.error(form.getFormHeader().getFormCode());
                        }
                    }
                }
            }
        }
        if (dataList != null && dataList.size() > 0) {
            invZzsSrcService.insertBySbs(txnDate, dataList);
        }
        return result;
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

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }
}
