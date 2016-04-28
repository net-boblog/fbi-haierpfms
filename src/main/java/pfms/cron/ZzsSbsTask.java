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
 * 从SBS取得增值税数据并插入原始数据表
 */
@Component
public class ZzsSbsTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String operId = "MPC1";                        // SBS柜员

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
            logger.info("----------[从SBS取得增值税数据]开始：" + ToolUtil.getDateTimeDashColon() + "----------");
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
            logger.info("----------[从SBS取得增值税数据]结束：" + ToolUtil.getDateTimeDashColon() + "----------");
        } catch (Exception e) {
            if (StringUtils.isNotEmpty(txnDate)) {
                syncFailService.insert(txnDate, datasrc);
            }
            logger.error("从SBS取得增值税数据失败！", e);
            throw new JobExecutionException("从SBS取得增值税数据失败！" + e == null ? "" : e.getMessage(), e);
        }
    }

    /**
     * 从SBS取得增值税数据
     *
     * @param txnDate
     * @return
     * @throws Exception
     */
    public boolean syncSBS(String txnDate) throws Exception {
        boolean result = true;
        String totcnt = "";
        String curcnt = "";
        int m = 0;//取整
        int n = 0;//取余
        Mn073 mn073 = new Mn073();
        mn073.setORDDAT(txnDate.replace("-", "")); // 日期
        mn073.setORDNUM("");      // 渠道号
        mn073.setANACDE("");      // 交易类别
        mn073.setBEGNUM("1");     // 起始笔数
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
                    logger.info("日期：" + txnDate + "没有增值税数据");
                } else {
                    result = false;
                    logger.error(form.getFormHeader().getFormCode());
                }
            }
        }
        if (!totcnt.isEmpty() && totcnt != "") {
            //因为 totcnt是全局变量，所有在第一次查询之后，发起第二次交易时totcnt就不为空，所有要在第一次发起交易时清空
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
