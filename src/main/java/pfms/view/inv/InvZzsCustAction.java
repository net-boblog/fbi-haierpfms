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
 * 增值税客户基本信息维护
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
    private List<SelectItem> zzsYbnsrList; // 一般纳税人下拉列表
    private List<InvZzsCust> invZzsCustList = new ArrayList<InvZzsCust>();
    private InvZzsCust invZzsCustAdd = new InvZzsCust();
    private InvZzsCust invZzsCustUpd = new InvZzsCust();
    private String khdm; // 客户代码
    private String khmc; // 客户名称

    @PostConstruct
    public void init() {
        zzsYbnsrList = EnumUtil.getYbnsrList();
        operInfo = operManager.getOperInfo();
    }

    /**
     * 查询
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
            logger.error("查询失败！", e);
            MessageUtil.addError("查询失败！" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * 查询添加
     */
    public void onQryAdd() {
        try {
            InvZzsCustExample example = new InvZzsCustExample();
            InvZzsCustExample.Criteria criteria = example.createCriteria();
            criteria.andCrtDateEqualTo(ToolUtil.getDateDash());
            example.setOrderByClause("CRT_DATE desc, CRT_TIME desc");
            invZzsCustList = invZzsCustService.selectByExample(example);
        } catch (Exception e) {
            logger.error("查询失败！", e);
            MessageUtil.addError("查询失败！" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * 添加
     */
    public void onAdd() {
        try {
            // 验证客户代码是否重复
            InvZzsCustExample example = new InvZzsCustExample();
            InvZzsCustExample.Criteria criteria = example.createCriteria();
            criteria.andKhdmEqualTo(invZzsCustAdd.getKhdm());
            int count = invZzsCustService.countByExample(example);
            if(count > 0) {
                MessageUtil.addError("客户代码已存在！");
                return;
            }

            invZzsCustAdd.setCrtOperId(operInfo.getOperId()); // 创建者ID
            boolean isSuccess = invZzsCustService.insert(invZzsCustAdd);
            if (isSuccess) {
                onQryAdd();
                MessageUtil.addInfo("添加成功！");
            } else {
                MessageUtil.addError("添加失败！");
            }
        } catch (Exception e) {
            logger.error("添加失败！", e);
            MessageUtil.addError("添加失败！" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * 选择
     *
     * @param invZzsCust
     */
    public void selectRecordAction(InvZzsCust invZzsCust) {
        try {
            invZzsCustUpd = invZzsCust;
        } catch (Exception e) {
            logger.error("更新失败！", e);
        }
    }

    /**
     * 更新
     */
    public void onUpd() {
        try {
            // 验证客户代码是否重复
            InvZzsCustExample example = new InvZzsCustExample();
            InvZzsCustExample.Criteria criteria = example.createCriteria();
            criteria.andPkidNotEqualTo(invZzsCustUpd.getPkid());
            criteria.andKhdmEqualTo(invZzsCustUpd.getKhdm());
            int count = invZzsCustService.countByExample(example);
            if(count > 0) {
                MessageUtil.addError("客户代码已存在！");
                return;
            }

            invZzsCustUpd.setUpdOperId(operInfo.getOperId()); // 修改者ID
            boolean isSuccess = invZzsCustService.update(invZzsCustUpd);
            if (isSuccess) {
                MessageUtil.addInfo("更新成功！");
            } else {
                MessageUtil.addError("更新失败！");
            }
        } catch (Exception e) {
            logger.error("更新失败！", e);
            MessageUtil.addError("更新失败！" + e == null ? "" : e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param pkid
     */
    public void onDelete(String pkid) {
        try {
            boolean isSuccess = invZzsCustService.deleteByPrimaryKey(pkid);
            if (isSuccess) {
                MessageUtil.addInfo("删除成功！");
            }
            onQry();
        } catch (Exception e) {
            logger.error("删除失败！", e);
            MessageUtil.addError("删除失败！" + e == null ? "" : e.getMessage());
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
