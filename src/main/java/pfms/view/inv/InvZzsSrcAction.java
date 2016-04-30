package pfms.view.inv;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pfms.enums.EnuZzsFpzl;
import pfms.enums.EnuZzsSrc;
import pfms.repository.model.custom.CustomInvZzsSrc;
import pfms.service.inv.InvZzsSrcService;
import pfms.util.EnumUtil;
import skyline.common.primefaces.MessageUtil;
import skyline.security.OperManager;
import skyline.service.OperInfo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.List;
import java.util.Map;

/**
 * 原始数据表
 */
@ManagedBean
@ViewScoped
public class InvZzsSrcAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ManagedProperty(value = "#{operManager}")
    private OperManager operManager;

    @ManagedProperty(value = "#{invZzsSrcService}")
    private InvZzsSrcService invZzsSrcService;

    private OperInfo operInfo;
    private EnuZzsSrc enuZzsSrc = EnuZzsSrc.SRC_00;
    private EnuZzsFpzl enuZzsFpzl = EnuZzsFpzl.FPZL_0;
    private List<CustomInvZzsSrc> customInvZzsSrcList;
    private List<SelectItem> zzsSrcList;       // 数据来源下拉列表
    private List<SelectItem> zzsFpzlList;      // 发票类别下拉列表
    private String fbtidx;                     // 流水号
    private String khmc;                       // 客户名称
    private String datasrc;                    // 数据来源
    private String txnDateStart;               // 交易日期开始
    private String txnDateEnd;                 // 交易日期结束
    private String fpzl;                       // 发票类别
    private CustomInvZzsSrc[] selectedRecords; // 选择数据

    @PostConstruct
    public void init() {
        txnDateStart = new DateTime().plusDays(-1).toString("yyyy-MM-dd");
        txnDateEnd = new DateTime().toString("yyyy-MM-dd");
        zzsSrcList = EnumUtil.getSrcList();
        zzsFpzlList = EnumUtil.getFpzlList();
        operInfo = operManager.getOperInfo();
    }

    /**
     * 查询未处理的数据
     */
    public void onQryUnProc() {
        try {
            fpzl = "";
            selectedRecords = null;
            CustomInvZzsSrc customInvZzsSrc = new CustomInvZzsSrc();
            if (StringUtils.isNotEmpty(fbtidx)) {
                customInvZzsSrc.setFbtidx(fbtidx);
            }
            if (StringUtils.isNotEmpty(khmc)) {
                customInvZzsSrc.setKhmc(khmc);
            }
            if (StringUtils.isNotEmpty(datasrc)) {
                customInvZzsSrc.setDatasrc(datasrc);
            }
            if (StringUtils.isNotEmpty(txnDateStart)) {
                customInvZzsSrc.setTxnDateStart(txnDateStart);
            }
            if (StringUtils.isNotEmpty(txnDateEnd)) {
                customInvZzsSrc.setTxnDateEnd(txnDateEnd);
            }

            customInvZzsSrcList = invZzsSrcService.selectUnProc(customInvZzsSrc);
        } catch (Exception e) {
            logger.error("查询失败！", e);
            MessageUtil.addError("查询失败！" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * 打印发票
     */
    public void onPrintFp() {
        try {
            if (selectedRecords == null || selectedRecords.length < 1) {
                MessageUtil.addError("请至少选择一笔记录。");
                return;
            }

            if (StringUtils.isEmpty(fpzl)) {
                MessageUtil.addError("请选择发票类别。");
                return;
            }

            for (CustomInvZzsSrc customInvZzsSrc : selectedRecords) {
                if (StringUtils.isEmpty(customInvZzsSrc.getKhswdjh())) {
                    MessageUtil.addError("选择记录中含有未录入客户信息的数据，请先录入客户信息。");
                    return;
                }
            }

            Map<String, Integer> result = invZzsSrcService.printFp(selectedRecords, fpzl, operInfo.getOperId());
            Integer totalCnt = result.get("totalCnt");     // 总笔数
            Integer successCnt = result.get("successCnt"); // 成功笔数
            Integer errorCnt = result.get("errorCnt");     // 失败笔数
            onQryUnProc();
            fpzl = "";
            if (totalCnt != null && totalCnt > 0) {
                MessageUtil.addInfo("总共" + totalCnt + " 笔。");
            }
            if (successCnt != null && successCnt > 0) {
                MessageUtil.addInfo("成功" + successCnt + " 笔。");
            }
            if (errorCnt != null && errorCnt > 0) {
                MessageUtil.addInfo("失败" + errorCnt + " 笔。");
            }
        } catch (Exception e) {
            logger.error("打印发票失败！", e);
            MessageUtil.addError("打印发票失败！" + e == null ? "" : e.getMessage());
        }
    }

    //==============================================get set=====================================================
    public OperManager getOperManager() {
        return operManager;
    }

    public void setOperManager(OperManager operManager) {
        this.operManager = operManager;
    }

    public InvZzsSrcService getInvZzsSrcService() {
        return invZzsSrcService;
    }

    public void setInvZzsSrcService(InvZzsSrcService invZzsSrcService) {
        this.invZzsSrcService = invZzsSrcService;
    }

    public OperInfo getOperInfo() {
        return operInfo;
    }

    public void setOperInfo(OperInfo operInfo) {
        this.operInfo = operInfo;
    }

    public EnuZzsSrc getEnuZzsSrc() {
        return enuZzsSrc;
    }

    public void setEnuZzsSrc(EnuZzsSrc enuZzsSrc) {
        this.enuZzsSrc = enuZzsSrc;
    }

    public EnuZzsFpzl getEnuZzsFpzl() {
        return enuZzsFpzl;
    }

    public void setEnuZzsFpzl(EnuZzsFpzl enuZzsFpzl) {
        this.enuZzsFpzl = enuZzsFpzl;
    }

    public List<SelectItem> getZzsFpzlList() {
        return zzsFpzlList;
    }

    public void setZzsFpzlList(List<SelectItem> zzsFpzlList) {
        this.zzsFpzlList = zzsFpzlList;
    }

    public List<CustomInvZzsSrc> getCustomInvZzsSrcList() {
        return customInvZzsSrcList;
    }

    public void setCustomInvZzsSrcList(List<CustomInvZzsSrc> customInvZzsSrcList) {
        this.customInvZzsSrcList = customInvZzsSrcList;
    }

    public List<SelectItem> getZzsSrcList() {
        return zzsSrcList;
    }

    public void setZzsSrcList(List<SelectItem> zzsSrcList) {
        this.zzsSrcList = zzsSrcList;
    }

    public String getFbtidx() {
        return fbtidx;
    }

    public void setFbtidx(String fbtidx) {
        this.fbtidx = fbtidx;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getDatasrc() {
        return datasrc;
    }

    public void setDatasrc(String datasrc) {
        this.datasrc = datasrc;
    }

    public String getTxnDateStart() {
        return txnDateStart;
    }

    public void setTxnDateStart(String txnDateStart) {
        this.txnDateStart = txnDateStart;
    }

    public String getTxnDateEnd() {
        return txnDateEnd;
    }

    public void setTxnDateEnd(String txnDateEnd) {
        this.txnDateEnd = txnDateEnd;
    }

    public String getFpzl() {
        return fpzl;
    }

    public void setFpzl(String fpzl) {
        this.fpzl = fpzl;
    }

    public CustomInvZzsSrc[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(CustomInvZzsSrc[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }
}
