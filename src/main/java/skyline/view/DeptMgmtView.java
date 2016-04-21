package skyline.view;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import skyline.common.MD5Helper;
import skyline.common.primefaces.MessageUtil;
import skyline.repository.model.Ptdept;
import skyline.repository.model.Ptoper;
import skyline.repository.model.Ptrole;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihe
 * on 2015/9/10.18:18
 */
@ManagedBean
@ViewScoped
public class DeptMgmtView {

    private String sql;
    private String deptId;
    private String deptName;
    private Boolean isReadonly = false;
    private TreeNode root1;
    private TreeNode pnode;
    private TreeNode selectedNode;
    private TreeNode[] selectedNode2;
    private List<Ptdept> deptList;
    private List<Ptdept> deptQryList;
    private List<SelectItem> deptQrys;
    private Ptdept ptdeptAdd;
    private Ptdept ptdeptQry;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ManagedProperty(value = "#{skylineJdbc}")
    private NamedParameterJdbcTemplate skylineJdbc;

    @PostConstruct
    public void init(){
        try {
            sql = "SELECT * FROM PTDEPT T ORDER BY T.DEPTID ASC";
            deptList = skylineJdbc.query(sql,new BeanPropertyRowMapper<Ptdept>(Ptdept.class));
        } catch (Exception e) {
            logger.error("查询数据时出现错误。", e);
            MessageUtil.addWarn("查询数据时出现错误。" + e.getMessage());
        }
        root1 = new DefaultTreeNode("Root", null);
        deptQrys = new ArrayList<>();
        for(Ptdept ptdept:deptList){
            if("0".equals(ptdept.getParentdeptid())){
                deptQrys.add(new SelectItem(ptdept.getDeptid(), ptdept.getDeptname()+"("+ptdept.getDeptid()+")"));
                pnode = new DefaultTreeNode(ptdept.getDeptname()+"("+ptdept.getDeptid()+")",root1);
                if(deptHasChildren(ptdept.getDeptid())){
                    deptTreeRecursion(ptdept.getDeptid(),pnode);
                }
            }
        }
        ptdeptAdd = new Ptdept();
        ptdeptQry = new Ptdept();
    }

    public void deptTreeRecursion(String treeId,TreeNode parentNode){
        for(Ptdept ptdeptR:deptList){
            if(ptdeptR.getParentdeptid().equals(treeId)){
                deptQrys.add(new SelectItem(ptdeptR.getDeptid(), ptdeptR.getDeptname()+"("+ptdeptR.getDeptid()+")"));
                TreeNode rnode = new DefaultTreeNode(ptdeptR.getDeptname()+"("+ptdeptR.getDeptid()+")",parentNode);
                if(deptHasChildren(ptdeptR.getDeptid())){
                    deptTreeRecursion(ptdeptR.getDeptid(),rnode);
                }
            }
        }
    }

    public boolean deptHasChildren(String treeId1){
        boolean childFlag = false;
        for(Ptdept ptdeptC:deptList){
            if(ptdeptC.getParentdeptid().equals(treeId1)){
                childFlag = true;
                break;
            }
        }
        return childFlag;
    }

    //保存
    private String strSubmitType = "deptAdd";
    public void onSave() {
        if ("deptAdd".equals(strSubmitType)) {
            if (hasEquals(ptdeptAdd.getDeptid(), ptdeptAdd.getDeptname())) {
                addMessage("新增机构代码或机构名称已存在，请确认后再次添加...");
                return;
            }
            if ("".equals(ptdeptAdd.getDeptid()) || "".equals(ptdeptAdd.getDeptname())) {
                addMessage("新增机构代码或机构名称不能为空，请确认后再次添加...");
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("parentdeptid", ptdeptAdd.getParentdeptid());
            paramMap.put("deptname", ptdeptAdd.getDeptname());
            paramMap.put("deptid", ptdeptAdd.getDeptid());
            paramMap.put("deptlevel", ptdeptAdd.getDeptlevel());
            paramMap.put("deptleaf", ptdeptAdd.getDeptleaf());
            paramMap.put("deptdesc", ptdeptAdd.getDeptdesc());
            paramMap.put("deptstatus", ptdeptAdd.getDeptstatus());
            sql = "insert into ptdept t (t.deptid,t.deptname,t.deptdesc,t.parentdeptid,t.deptleaf," +
                    "t.deptlevel,t.deptstatus,t.filldate1) " +
                    "values (:deptid,:deptname,:deptdesc,:parentdeptid,:deptleaf,:deptlevel,:deptstatus,sysdate)";
            skylineJdbc.update(sql, paramMap);
            ptdeptAdd = new Ptdept();
            successMessage("新增完成!...");
        } else if ("deptUpd".equals(strSubmitType)) {
//            if (hasEquals("", ptdeptAdd.getDeptname())) {
//                addMessage("机构名称已存在，请确认后再次添加...");
//                return;
//            }
            if ("".equals(ptdeptAdd.getDeptname())) {
                addMessage("机构名称不能为空，请确认后再次添加...");
                return;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("parentdeptid", ptdeptAdd.getParentdeptid());
            paramMap.put("deptname", ptdeptAdd.getDeptname());
            paramMap.put("deptid", ptdeptAdd.getDeptid());
            paramMap.put("deptlevel", ptdeptAdd.getDeptlevel());
            paramMap.put("deptleaf", ptdeptAdd.getDeptleaf());
            paramMap.put("deptdesc", ptdeptAdd.getDeptdesc());
            paramMap.put("deptstatus", ptdeptAdd.getDeptstatus());
            sql = "update ptdept t set t.deptname =:deptname,t.deptdesc =:deptdesc,t.parentdeptid =:parentdeptid,t.deptleaf =:deptleaf, t.deptlevel =:deptlevel,t.deptstatus =:deptstatus" +
                    " where t.deptid = :deptid";
            skylineJdbc.update(sql, paramMap);
            ptdeptAdd = new Ptdept();
            strSubmitType = "deptAdd";
            isReadonly = false;
            successMessage("修改完成!...");
        }
        init();
        onQuery();
    }
    //    用户列表查询
    public void onQuery(){
        try {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("parentdeptid",ptdeptQry.getParentdeptid());
            paramMap.put("deptid",ptdeptQry.getDeptid());
            sql = "SELECT T.* FROM PTDEPT t WHERE 1=1 ";
            if(!StringUtils.isBlank(ptdeptQry.getParentdeptid())){
                sql += "AND t.parentdeptid =:parentdeptid ";
            }
            if(!StringUtils.isBlank(ptdeptQry.getDeptid())){
                sql += "AND t.deptid||t.deptname like '%"+ptdeptQry.getDeptid().trim()+"%'";
            }
            sql += "ORDER BY parentdeptid,deptid ASC ";
            deptQryList = skylineJdbc.query(sql,paramMap, new BeanPropertyRowMapper<Ptdept>(Ptdept.class));
        } catch (Exception e) {
            logger.error("查询数据时出现错误。", e);
            MessageUtil.addWarn("查询数据时出现错误。" + e.getMessage());
        }
    }

    //    机构操作
    public void selectRecordAction(Ptdept strSubmitTypePara){
        try{
            ptdeptAdd = (Ptdept) BeanUtils.cloneBean(strSubmitTypePara);
            strSubmitType = "deptUpd";
            isReadonly = true;
        } catch (Exception e){
            logger.error("编辑数据时出现错误。", e);
            MessageUtil.addWarn("编辑数据时出现错误。" + e.getMessage());
        }
    }

    //    判断是否有已存在相同名字或代码的机构
    public boolean hasEquals(String compareId,String compareName){
        boolean compareFlag = false;
        for(Ptdept ptdept:deptList){
            if(compareId.equals(ptdept.getDeptid())||compareName.equals(ptdept.getDeptname())){
                compareFlag = true;
                break;
            }
        }
        return compareFlag;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"警告",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
    }
    public void successMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"通知",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Ptdept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Ptdept> deptList) {
        this.deptList = deptList;
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode[] getSelectedNode2() {
        return selectedNode2;
    }

    public void setSelectedNode2(TreeNode[] selectedNode2) {
        this.selectedNode2 = selectedNode2;
    }

    public NamedParameterJdbcTemplate getSkylineJdbc() {
        return skylineJdbc;
    }

    public void setSkylineJdbc(NamedParameterJdbcTemplate skylineJdbc) {
        this.skylineJdbc = skylineJdbc;
    }

    public Ptdept getPtdeptAdd() {
        return ptdeptAdd;
    }

    public void setPtdeptAdd(Ptdept ptdeptAdd) {
        this.ptdeptAdd = ptdeptAdd;
    }

    public Ptdept getPtdeptQry() {
        return ptdeptQry;
    }

    public void setPtdeptQry(Ptdept ptdeptQry) {
        this.ptdeptQry = ptdeptQry;
    }

    public List<SelectItem> getDeptQrys() {
        return deptQrys;
    }

    public void setDeptQrys(List<SelectItem> deptQrys) {
        this.deptQrys = deptQrys;
    }

    public List<Ptdept> getDeptQryList() {
        return deptQryList;
    }

    public void setDeptQryList(List<Ptdept> deptQryList) {
        this.deptQryList = deptQryList;
    }

    public Boolean getIsReadonly() {
        return isReadonly;
    }

    public void setIsReadonly(Boolean isReadonly) {
        this.isReadonly = isReadonly;
    }
}
