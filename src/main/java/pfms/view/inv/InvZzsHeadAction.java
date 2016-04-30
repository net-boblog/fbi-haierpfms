package pfms.view.inv;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pfms.enums.EnuZzsFpzl;
import pfms.enums.EnuZzsSrc;
import pfms.repository.model.custom.CustomInvZzsHead;
import pfms.service.inv.InvZzsHeadService;
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

/**
 * ��ֵ˰���۷�Ʊ
 */
@ManagedBean
@ViewScoped
public class InvZzsHeadAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ManagedProperty(value = "#{operManager}")
    private OperManager operManager;

    @ManagedProperty(value = "#{invZzsHeadService}")
    private InvZzsHeadService invZzsHeadService;

    private EnuZzsSrc enuZzsSrc = EnuZzsSrc.SRC_00;
    private EnuZzsFpzl enuZzsFpzl = EnuZzsFpzl.FPZL_0;
    private List<CustomInvZzsHead> customInvZzsHeadList;
    private List<SelectItem> zzsSrcList; // ������Դ�����б�
    private List<SelectItem> zzsFpzlList; // ��Ʊ��������б�
    private String fbtidx; // ��ˮ��
    private String khmc;   // �ͻ�����
    private String datasrc; // ������Դ
    private String kprq; // ��������
    private String fpzl;    // ��Ʊ���
    private OperInfo operInfo;

    @PostConstruct
    public void init() {
        kprq = new DateTime().toString("yyyy-MM-dd");
        zzsSrcList = EnumUtil.getSrcList();
        zzsFpzlList = EnumUtil.getFpzlList();
        operInfo = operManager.getOperInfo();
    }

    /**
     * ��ѯ����Ʊ����
     */
    public void onQryUnPrint() {
        try {
            CustomInvZzsHead customInvZzsHead = new CustomInvZzsHead();
            if (StringUtils.isNotEmpty(fbtidx)) {
                customInvZzsHead.setFbtidx(fbtidx);
            }
            if (StringUtils.isNotEmpty(khmc)) {
                customInvZzsHead.setKhmc(khmc);
            }
            if (StringUtils.isNotEmpty(datasrc)) {
                customInvZzsHead.setDatasrc(datasrc);
            }
            if (StringUtils.isNotEmpty(fpzl)) {
                customInvZzsHead.setFpzl(fpzl);
            }
            customInvZzsHeadList = invZzsHeadService.selectUnPrint(customInvZzsHead);
        } catch (Exception e) {
            logger.error("��ѯʧ�ܣ�", e);
            MessageUtil.addError("��ѯʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ��ѯ�ѿ�Ʊ�ɹ�����
     */
    public void onQryPrint() {
        try {
            CustomInvZzsHead customInvZzsHead = new CustomInvZzsHead();
            if (StringUtils.isNotEmpty(fbtidx)) {
                customInvZzsHead.setFbtidx(fbtidx);
            }
            if (StringUtils.isNotEmpty(khmc)) {
                customInvZzsHead.setKhmc(khmc);
            }
            if (StringUtils.isNotEmpty(datasrc)) {
                customInvZzsHead.setDatasrc(datasrc);
            }
            if (StringUtils.isNotEmpty(kprq)) {
                customInvZzsHead.setKprq(new DateTime(kprq).toDate());
            }
            if (StringUtils.isNotEmpty(fpzl)) {
                customInvZzsHead.setFpzl(fpzl);
            }
            customInvZzsHeadList = invZzsHeadService.selectPrint(customInvZzsHead);
        } catch (Exception e) {
            logger.error("��ѯʧ�ܣ�", e);
            MessageUtil.addError("��ѯʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ��ѯ�ѿ�Ʊʧ������
     */
    public void onQryPrintFail() {
        try {
            CustomInvZzsHead customInvZzsHead = new CustomInvZzsHead();
            if (StringUtils.isNotEmpty(fbtidx)) {
                customInvZzsHead.setFbtidx(fbtidx);
            }
            if (StringUtils.isNotEmpty(khmc)) {
                customInvZzsHead.setKhmc(khmc);
            }
            if (StringUtils.isNotEmpty(datasrc)) {
                customInvZzsHead.setDatasrc(datasrc);
            }
            if (StringUtils.isNotEmpty(kprq)) {
                customInvZzsHead.setKprq(new DateTime(kprq).toDate());
            }
            if (StringUtils.isNotEmpty(fpzl)) {
                customInvZzsHead.setFpzl(fpzl);
            }
            customInvZzsHeadList = invZzsHeadService.selectPrintFail(customInvZzsHead);
        } catch (Exception e) {
            logger.error("��ѯʧ�ܣ�", e);
            MessageUtil.addError("��ѯʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ��ѯ�����ϵ�����
     */
    public void onQryUnZuoFei() {
        try {
            CustomInvZzsHead customInvZzsHead = new CustomInvZzsHead();
            if (StringUtils.isNotEmpty(fbtidx)) {
                customInvZzsHead.setFbtidx(fbtidx);
            }
            if (StringUtils.isNotEmpty(khmc)) {
                customInvZzsHead.setKhmc(khmc);
            }
            if (StringUtils.isNotEmpty(datasrc)) {
                customInvZzsHead.setDatasrc(datasrc);
            }
            if (StringUtils.isNotEmpty(fpzl)) {
                customInvZzsHead.setFpzl(fpzl);
            }
            customInvZzsHeadList = invZzsHeadService.selectUnZuoFei(customInvZzsHead);
        } catch (Exception e) {
            logger.error("��ѯʧ�ܣ�", e);
            MessageUtil.addError("��ѯʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ����
     *
     * @param customInvZzsHead
     */
    public void onZuoFui(CustomInvZzsHead customInvZzsHead) {
        try {
            if (StringUtils.isEmpty(customInvZzsHead.getInvoicecode())) {
                MessageUtil.addError("û�з�Ʊ���벻�����ϡ�");
                return;
            }
            invZzsHeadService.zuoFei(customInvZzsHead, operInfo.getOperId());
            onQryPrint();
            MessageUtil.addInfo("���Ϸ�Ʊ�ɹ���");
        } catch (Exception e) {
            logger.error("���Ϸ�Ʊʧ�ܣ�", e);
            MessageUtil.addError("���Ϸ�Ʊʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ��ѯ����������
     */
    public void onQryZuoFei() {
        try {
            CustomInvZzsHead customInvZzsHead = new CustomInvZzsHead();
            if (StringUtils.isNotEmpty(fbtidx)) {
                customInvZzsHead.setFbtidx(fbtidx);
            }
            if (StringUtils.isNotEmpty(khmc)) {
                customInvZzsHead.setKhmc(khmc);
            }
            if (StringUtils.isNotEmpty(datasrc)) {
                customInvZzsHead.setDatasrc(datasrc);
            }
            if (StringUtils.isNotEmpty(kprq)) {
                customInvZzsHead.setKprq(new DateTime(kprq).toDate());
            }
            if (StringUtils.isNotEmpty(fpzl)) {
                customInvZzsHead.setFpzl(fpzl);
            }
            customInvZzsHeadList = invZzsHeadService.selectZuoFei(customInvZzsHead);
        } catch (Exception e) {
            logger.error("��ѯʧ�ܣ�", e);
            MessageUtil.addError("��ѯʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    //==============================================get set=====================================================
    public OperManager getOperManager() {
        return operManager;
    }

    public void setOperManager(OperManager operManager) {
        this.operManager = operManager;
    }

    public InvZzsHeadService getInvZzsHeadService() {
        return invZzsHeadService;
    }

    public void setInvZzsHeadService(InvZzsHeadService invZzsHeadService) {
        this.invZzsHeadService = invZzsHeadService;
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

    public List<CustomInvZzsHead> getCustomInvZzsHeadList() {
        return customInvZzsHeadList;
    }

    public void setCustomInvZzsHeadList(List<CustomInvZzsHead> customInvZzsHeadList) {
        this.customInvZzsHeadList = customInvZzsHeadList;
    }

    public List<SelectItem> getZzsSrcList() {
        return zzsSrcList;
    }

    public void setZzsSrcList(List<SelectItem> zzsSrcList) {
        this.zzsSrcList = zzsSrcList;
    }

    public List<SelectItem> getZzsFpzlList() {
        return zzsFpzlList;
    }

    public void setZzsFpzlList(List<SelectItem> zzsFpzlList) {
        this.zzsFpzlList = zzsFpzlList;
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

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getFpzl() {
        return fpzl;
    }

    public void setFpzl(String fpzl) {
        this.fpzl = fpzl;
    }
}
