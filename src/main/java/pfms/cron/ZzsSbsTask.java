package pfms.cron;

import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.form.re.T467;
import gateway.sbs.txn.model.msg.Mn073;
import org.joda.time.DateTime;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pfms.service.DataExchangeService;
import pfms.service.inv.InvZzsSrcService;
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
    private DataExchangeService dataExchangeService;

    public void runTask() throws JobExecutionException {
        try {
            logger.info("----------[��SBSȡ����ֵ˰����]��ʼ��" + ToolUtil.getDateTimeDashColon() + "----------");
            String txnDate = new DateTime().plusDays(-1).toString("yyyy-MM-dd");
            txnDate = "2016-04-20";
            List<T467.Bean> dataList = getZzsFromSbs(txnDate.replace("-", ""));
            if (dataList != null && dataList.size() > 0) {
                invZzsSrcService.insertBySbs(txnDate, dataList);
            }
            logger.info("----------[��SBSȡ����ֵ˰����]������" + ToolUtil.getDateTimeDashColon() + "----------");
        } catch (Exception e) {
            logger.error("��SBSȡ����ֵ˰����ʧ�ܣ�", e);
            throw new JobExecutionException("��SBSȡ����ֵ˰����ʧ�ܣ�" + e == null ? "" : e.getMessage(), e);
        }
    }

    /**
     * ��SBSȡ����ֵ˰
     */
    public List<T467.Bean> getZzsFromSbs(String txnDate) throws Exception {
        logger.info("----------[����n073-��ֵ˰�������ݲ�ѯ]��ʼ��" + ToolUtil.getDateTimeDashColon() + "----------");
        String totcnt = "";
        String curcnt = "";
        int m = 0;//ȡ��
        int n = 0;//ȡ��
        Mn073 mn073 = new Mn073();
        mn073.setORDDAT(txnDate); // ����
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
                } else {
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
                            logger.error(form.getFormHeader().getFormCode());
                        }
                    }
                }
            }
        }
        logger.info("----------[����n073-��ֵ˰�������ݲ�ѯ]������" + ToolUtil.getDateTimeDashColon() + "----------");
        return dataList;
    }

    //==============================================get set=====================================================
    public InvZzsSrcService getInvZzsSrcService() {
        return invZzsSrcService;
    }

    public void setInvZzsSrcService(InvZzsSrcService invZzsSrcService) {
        this.invZzsSrcService = invZzsSrcService;
    }

    public DataExchangeService getDataExchangeService() {
        return dataExchangeService;
    }

    public void setDataExchangeService(DataExchangeService dataExchangeService) {
        this.dataExchangeService = dataExchangeService;
    }
}
