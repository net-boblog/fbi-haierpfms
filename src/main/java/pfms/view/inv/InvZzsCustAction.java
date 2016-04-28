package pfms.view.inv;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pfms.enums.EnuZzsYbnsrFlag;
import pfms.repository.model.InvZzsCust;
import pfms.repository.model.InvZzsCustExample;
import pfms.service.inv.InvZzsCustService;
import pfms.util.EnumUtil;
import pfms.util.ToolUtil;
import skyline.common.primefaces.MessageUtil;
import skyline.security.OperManager;
import skyline.service.OperInfo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ֵ˰�ͻ�������Ϣά��
 */
@ManagedBean
@ViewScoped
public class InvZzsCustAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ManagedProperty(value = "#{operManager}")
    private OperManager operManager;

    @ManagedProperty(value = "#{invZzsCustService}")
    private InvZzsCustService invZzsCustService;

    private OperInfo operInfo;
    private EnuZzsYbnsrFlag enuZzsYbnsrFlag = EnuZzsYbnsrFlag.YBNSR_FLAG_0;
    private List<SelectItem> zzsYbnsrList; // һ����˰�������б�
    private List<InvZzsCust> invZzsCustList = new ArrayList<InvZzsCust>();
    private InvZzsCust invZzsCustAdd = new InvZzsCust();
    private InvZzsCust invZzsCustUpd = new InvZzsCust();
    private String khdm; // �ͻ�����
    private String khmc; // �ͻ�����

    @PostConstruct
    public void init() {
        zzsYbnsrList = EnumUtil.getYbnsrList();
        operInfo = operManager.getOperInfo();
    }

    /**
     * ��ѯ
     */
    public void onQry() {
        try {
            InvZzsCustExample example = new InvZzsCustExample();
            InvZzsCustExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotEmpty(khdm)) {
                criteria.andKhdmLike("%" + khdm + "%");
            }
            if (StringUtils.isNotEmpty(khmc)) {
                criteria.andKhmcLike("%" + khmc + "%");
            }
            example.setOrderByClause("KHDM");
            invZzsCustList = invZzsCustService.selectByExample(example);
        } catch (Exception e) {
            logger.error("��ѯʧ�ܣ�", e);
            MessageUtil.addError("��ѯʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ��ѯ���
     */
    public void onQryAdd() {
        try {
            InvZzsCustExample example = new InvZzsCustExample();
            InvZzsCustExample.Criteria criteria = example.createCriteria();
            criteria.andCrtDateEqualTo(ToolUtil.getDateDash());
            example.setOrderByClause("CRT_DATE desc, CRT_TIME desc");
            invZzsCustList = invZzsCustService.selectByExample(example);
        } catch (Exception e) {
            logger.error("��ѯʧ�ܣ�", e);
            MessageUtil.addError("��ѯʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ���
     */
    public void onAdd() {
        try {
            // ��֤�ͻ������Ƿ��ظ�
            InvZzsCustExample example = new InvZzsCustExample();
            InvZzsCustExample.Criteria criteria = example.createCriteria();
            criteria.andKhdmEqualTo(invZzsCustAdd.getKhdm());
            int count = invZzsCustService.countByExample(example);
            if(count > 0) {
                MessageUtil.addError("�ͻ������Ѵ��ڣ�");
                return;
            }

            invZzsCustAdd.setCrtOperId(operInfo.getOperId()); // ������ID
            boolean isSuccess = invZzsCustService.insert(invZzsCustAdd);
            if (isSuccess) {
                onQryAdd();
                MessageUtil.addInfo("��ӳɹ���");
            } else {
                MessageUtil.addError("���ʧ�ܣ�");
            }
        } catch (Exception e) {
            logger.error("���ʧ�ܣ�", e);
            MessageUtil.addError("���ʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ѡ��
     *
     * @param invZzsCust
     */
    public void selectRecordAction(InvZzsCust invZzsCust) {
        try {
            invZzsCustUpd = invZzsCust;
        } catch (Exception e) {
            logger.error("����ʧ�ܣ�", e);
        }
    }

    /**
     * ����
     */
    public void onUpd() {
        try {
            // ��֤�ͻ������Ƿ��ظ�
            InvZzsCustExample example = new InvZzsCustExample();
            InvZzsCustExample.Criteria criteria = example.createCriteria();
            criteria.andPkidNotEqualTo(invZzsCustUpd.getPkid());
            criteria.andKhdmEqualTo(invZzsCustUpd.getKhdm());
            int count = invZzsCustService.countByExample(example);
            if(count > 0) {
                MessageUtil.addError("�ͻ������Ѵ��ڣ�");
                return;
            }

            invZzsCustUpd.setUpdOperId(operInfo.getOperId()); // �޸���ID
            boolean isSuccess = invZzsCustService.update(invZzsCustUpd);
            if (isSuccess) {
                MessageUtil.addInfo("���³ɹ���");
            } else {
                MessageUtil.addError("����ʧ�ܣ�");
            }
        } catch (Exception e) {
            logger.error("����ʧ�ܣ�", e);
            MessageUtil.addError("����ʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * ɾ��
     *
     * @param pkid
     */
    public void onDelete(String pkid) {
        try {
            boolean isSuccess = invZzsCustService.deleteByPrimaryKey(pkid);
            if (isSuccess) {
                MessageUtil.addInfo("ɾ���ɹ���");
            }
            onQry();
        } catch (Exception e) {
            logger.error("ɾ��ʧ�ܣ�", e);
            MessageUtil.addError("ɾ��ʧ�ܣ�" + e == null ? "" : e.getMessage());
        }
    }

    //==============================================get set=====================================================
    public OperManager getOperManager() {
        return operManager;
    }

    public void setOperManager(OperManager operManager) {
        this.operManager = operManager;
    }

    public InvZzsCustService getInvZzsCustService() {
        return invZzsCustService;
    }

    public void setInvZzsCustService(InvZzsCustService invZzsCustService) {
        this.invZzsCustService = invZzsCustService;
    }

    public EnuZzsYbnsrFlag getEnuZzsYbnsrFlag() {
        return enuZzsYbnsrFlag;
    }

    public void setEnuZzsYbnsrFlag(EnuZzsYbnsrFlag enuZzsYbnsrFlag) {
        this.enuZzsYbnsrFlag = enuZzsYbnsrFlag;
    }

    public List<SelectItem> getZzsYbnsrList() {
        return zzsYbnsrList;
    }

    public void setZzsYbnsrList(List<SelectItem> zzsYbnsrList) {
        this.zzsYbnsrList = zzsYbnsrList;
    }

    public List<InvZzsCust> getInvZzsCustList() {
        return invZzsCustList;
    }

    public void setInvZzsCustList(List<InvZzsCust> invZzsCustList) {
        this.invZzsCustList = invZzsCustList;
    }

    public InvZzsCust getInvZzsCustAdd() {
        return invZzsCustAdd;
    }

    public void setInvZzsCustAdd(InvZzsCust invZzsCustAdd) {
        this.invZzsCustAdd = invZzsCustAdd;
    }

    public InvZzsCust getInvZzsCustUpd() {
        return invZzsCustUpd;
    }

    public void setInvZzsCustUpd(InvZzsCust invZzsCustUpd) {
        this.invZzsCustUpd = invZzsCustUpd;
    }

    public String getKhdm() {
        return khdm;
    }

    public void setKhdm(String khdm) {
        this.khdm = khdm;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }
}
