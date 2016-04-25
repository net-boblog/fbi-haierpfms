package skyline.view;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import skyline.common.MD5Helper;
import skyline.common.primefaces.MessageUtil;
import skyline.repository.model.Ptdept;
import skyline.repository.model.Ptoper;
import skyline.repository.model.Ptrole;
import skyline.service.PlatformService;

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
 * on 2016/1/13.09:18
 */
@ManagedBean
@ViewScoped
public class OperInfoMgmtView {

    private String sql;
    private String sqlD;
    private String deptId;
    private String deptName;
    private TreeNode root1;
    private TreeNode root2;
    private TreeNode pnode;
    private TreeNode selectedNode;
    private TreeNode[] selectedNode2;
    private List<Ptoper> operList;
    private Ptoper ptoperAdd;
    private Ptoper ptoperUpd;
    private Ptoper ptoperQry;
    private Boolean isReOperPasswd = false;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ManagedProperty(value = "#{skylineJdbc}")
    private NamedParameterJdbcTemplate skylineJdbc;

    @ManagedProperty(value = "#{platformService}")
    private PlatformService platformService;

    @PostConstruct
    public void init(){
        ptoperAdd = new Ptoper();
        ptoperUpd = new Ptoper();
        ptoperQry = new Ptoper();
    }

    //    用户列表查询
    public void onQuery(){
        try {
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("deptid",ptoperQry.getDeptid());
            paramMap.put("operid",ptoperQry.getOperid());
            paramMap.put("operenabled",ptoperQry.getOperenabled());
            sql = "SELECT * FROM PTOPER T WHERE 1=1 ";
            if(!StringUtils.isEmpty(ptoperQry.getDeptid())) {
                sql += "AND T.DEPTID =:deptid ";
            }
            if(!StringUtils.isEmpty(ptoperQry.getOperid())) {
                sql += "AND T.OPERID||T.OPERNAME LIKE '%"+ptoperQry.getOperid()+"%' ";
            }
            if(!StringUtils.isEmpty(ptoperQry.getOperenabled())) {
                sql += "AND T.OPERENABLED =:operenabled ";
            }
            sql += "ORDER BY T.DEPTID,T.OPERID ASC ";
            operList = skylineJdbc.query(sql,paramMap, new BeanPropertyRowMapper<Ptoper>(Ptoper.class));
        } catch (Exception e) {
            logger.error("查询数据时出现错误。", e);
            MessageUtil.addWarn("查询数据时出现错误。" + e.getMessage());
        }
    }

    //保存
    private String strSubmitType = "operAdd";
    public void onSave(){
        try {
            if("operAdd".equals(strSubmitType)) {
                if (StringUtils.isEmpty(ptoperAdd.getOperid()) || StringUtils.isEmpty(ptoperAdd.getOpername())) {
                    addMessage("用户ID和用户姓名不能为空!...");
                    return;
                }
                if(hasEquals(ptoperAdd.getOperid())){
                    addMessage("用户ID已存在!...");
                    return;
                }
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deptid", ptoperAdd.getDeptid());
                paramMap.put("operid", ptoperAdd.getOperid());
                paramMap.put("opername", ptoperAdd.getOpername());
                paramMap.put("sex", ptoperAdd.getSex());
                paramMap.put("issuper", ptoperAdd.getIssuper());//是否领导
                paramMap.put("operphone", ptoperAdd.getOperphone());
                paramMap.put("mobilephone", ptoperAdd.getMobilephone());
                paramMap.put("otherphone", ptoperAdd.getOtherphone());
                paramMap.put("operenabled", ptoperAdd.getOperenabled());//账号是否可用
                paramMap.put("opertype", ptoperAdd.getOpertype());//类别
                paramMap.put("email", ptoperAdd.getEmail());
                paramMap.put("operpasswd", platformService.getCipherPassword(ptoperAdd.getOperid(),ptoperAdd.getOperpasswd()));
                sqlD = "insert into ptoper  (issuper,sex,deptid,operphone,operid,otherphone,operenabled,opername,mobilephone,opertype,operpasswd,email,filldate1 ) " +
                        "values (:issuper, :sex, :deptid, :operphone, :operid, :otherphone, :operenabled,:opername, :mobilephone, :opertype, :operpasswd, :email,sysdate)";
                skylineJdbc.update(sqlD, paramMap);
                ptoperAdd = new Ptoper();
                successMessage("新增成功!...");
            } else if ("operUpd".equals(strSubmitType)) {
                if (StringUtils.isEmpty(ptoperUpd.getOpername())) {
                    addMessage("用户姓名不能为空!...");
                    return;
                }
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("deptid", ptoperUpd.getDeptid());
                paramMap.put("operid", ptoperUpd.getOperid());
                paramMap.put("opername", ptoperUpd.getOpername());
                paramMap.put("sex", ptoperUpd.getSex());
                paramMap.put("issuper", ptoperUpd.getIssuper());//是否领导
                paramMap.put("operphone", ptoperUpd.getOperphone());
                paramMap.put("mobilephone", ptoperUpd.getMobilephone());
                paramMap.put("otherphone", ptoperUpd.getOtherphone());
                paramMap.put("operenabled", ptoperUpd.getOperenabled());//账号是否可用
                paramMap.put("opertype", ptoperUpd.getOpertype());//类别
                paramMap.put("email", ptoperUpd.getEmail());
                paramMap.put("operpasswd", platformService.getCipherPassword(ptoperUpd.getOperid(), "666666"));
                sqlD = "UPDATE ptoper j SET j.issuper = :issuper,j.sex = :sex,j.deptid = :deptid,j.operphone = :operphone," +
                        "j.otherphone = :otherphone,j.operenabled = :operenabled,j.opername = :opername," +
                        "j.mobilephone = :mobilephone,j.opertype = :opertype," +
                        "j.email = :email ";
                if (isReOperPasswd){
                    sqlD += ",j.operpasswd=:operpasswd  ";
                }
                sqlD += "where operid = :operid";
                skylineJdbc.update(sqlD, paramMap);

                successMessage("修改成功!...");
                if (isReOperPasswd)
                    successMessage("密码重置为666666");
                ptoperUpd = new Ptoper();
                strSubmitType = "operAdd";
                isReOperPasswd = false;
            }
            displayFlag = "block_none";
        } catch (Exception e){
            logger.error("编辑数据时出现错误。", e);
            MessageUtil.addWarn("编辑数据时出现错误。" + e.getMessage());
        }
        onQuery();
    }

    private String displayFlag = "block_none";
    public void selectRecordAction(Ptoper ptoperSelectedPara){
        try {
            ptoperUpd = (Ptoper) BeanUtils.cloneBean(ptoperSelectedPara);
            strSubmitType = "operUpd";
            displayFlag = "none_block";
        } catch (Exception e) {
            logger.error("查询数据时出现错误。", e);
            MessageUtil.addWarn("查询数据时出现错误。" + e.getMessage());
        }
    }
    //判断是否有已存在相同用户ID；
    public boolean hasEquals(String operid){
        boolean compareFlag = false;
        if (getOperEqList().contains(operid))
            compareFlag = true;
        return compareFlag;
    }
    public List<String> getOperEqList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String sql = "SELECT T.OPERID FROM PTOPER T WHERE 1=1 ";
        return skylineJdbc.queryForList(sql, paramMap, String.class);
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

    public List<Ptoper> getOperList() {
        return operList;
    }

    public void setOperList(List<Ptoper> operList) {
        this.operList = operList;
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }

    public TreeNode getRoot2() {
        return root2;
    }

    public void setRoot2(TreeNode root2) {
        this.root2 = root2;
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

    public Ptoper getPtoperAdd() {
        return ptoperAdd;
    }

    public void setPtoperAdd(Ptoper ptoperAdd) {
        this.ptoperAdd = ptoperAdd;
    }

    public Ptoper getPtoperQry() {
        return ptoperQry;
    }

    public void setPtoperQry(Ptoper ptoperQry) {
        this.ptoperQry = ptoperQry;
    }

    public String getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(String displayFlag) {
        this.displayFlag = displayFlag;
    }

    public Boolean getIsReOperPasswd() {
        return isReOperPasswd;
    }

    public void setIsReOperPasswd(Boolean isReOperPasswd) {
        this.isReOperPasswd = isReOperPasswd;
    }

    public Ptoper getPtoperUpd() {
        return ptoperUpd;
    }

    public void setPtoperUpd(Ptoper ptoperUpd) {
        this.ptoperUpd = ptoperUpd;
    }

    public PlatformService getPlatformService() {
        return platformService;
    }

    public void setPlatformService(PlatformService platformService) {
        this.platformService = platformService;
    }
}
