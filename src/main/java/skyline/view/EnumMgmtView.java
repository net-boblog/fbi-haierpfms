package skyline.view;

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import skyline.common.primefaces.MessageUtil;
import skyline.repository.model.Ptenudetail;
import skyline.repository.model.Ptenumain;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class EnumMgmtView implements Serializable  {

    private String sql;
    private String sqlD;
    private String enumId;
    private String enumName;
    private String strSubmitType;

    private TreeNode root1;
    private TreeNode pnode;
    private TreeNode selectedNode;
    private Ptenumain ptenumainAdd;
    private Ptenumain ptenumainUpd;
    private Ptenumain ptenumainDel;
    private Ptenudetail ptenudetailAdd;
    private Ptenudetail ptenudetailUpd;
    private Ptenudetail ptenudetailDel;
    private List<Ptenumain> enumainList;
    private List<Ptenudetail> enudetailList;
    private List<SelectItem> treeEnus;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ManagedProperty(value = "#{skylineJdbc}")
    private NamedParameterJdbcTemplate skylineJdbc;

    @PostConstruct
    public void init(){
        try {
            sql = "SELECT * FROM PTENUMAIN T ORDER BY T.ENUTYPE ASC ";
            enumainList = skylineJdbc.query(sql,new BeanPropertyRowMapper<Ptenumain>(Ptenumain.class));
        } catch (Exception e) {
            logger.error("查询数据时出现错误。", e);
            MessageUtil.addWarn("查询数据时出现错误。" + e.getMessage());
        }
        root1 = new DefaultTreeNode("Root", null);
        treeEnus = new ArrayList<SelectItem>();
        for(Ptenumain thrmenumain:enumainList){
            treeEnus.add(new SelectItem(thrmenumain.getEnutype(),thrmenumain.getEnuname()));
            pnode = new DefaultTreeNode(thrmenumain.getEnuname()+"("+thrmenumain.getEnutype()+")",root1);
        }
        ptenumainAdd = new Ptenumain();
        ptenumainUpd = new Ptenumain();
        ptenumainDel = new Ptenumain();
        ptenudetailAdd = new Ptenudetail();
        ptenudetailUpd = new Ptenudetail();
        ptenudetailDel = new Ptenudetail();
    }

    public void onQuery(){
        try {
            enumId = selectedNode.getData().toString();
            enumId = enumId.substring(enumId.lastIndexOf("(")+1,enumId.lastIndexOf(")"));
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("enutype",enumId);
            sql = "SELECT * from PTENUDETAIL t WHERE t.enutype =:enutype ORDER BY t.dispno ASC ";
            enudetailList = skylineJdbc.query(sql,paramMap, new BeanPropertyRowMapper<Ptenudetail>(Ptenudetail.class));
        } catch (Exception e) {
            logger.error("查询数据时出现错误。", e);
            MessageUtil.addWarn("查询数据时出现错误。" + e.getMessage());
        }
    }

    public void treeMenuAction(String strAddType){
        enumId = selectedNode.getData().toString();
        enumName = enumId.substring(0,enumId.lastIndexOf("("));
        enumId = enumId.substring(enumId.lastIndexOf("(")+1,enumId.lastIndexOf(")"));
        if("treeAdd".equals(strAddType)){
            ptenumainAdd = new Ptenumain();
        }else if("treeUpd".equals(strAddType)){
            ptenumainUpd.setEnutype(enumId);
            ptenumainUpd.setEnuname(enumName);
        }else if("treeDel".equals(strAddType)){
            ptenumainDel.setEnutype(enumId);
            ptenumainDel.setEnuname(enumName);
        }
    }

    //  操作主菜单
    public void addTreeMenuAction (String addFlag) {
        try {
            if("treeAdd".equals(addFlag)){
                if(hasEquEnu(ptenumainAdd.getEnutype())){
                    addMessage("枚举类型代码已存在，请查证后再操作...");
                    return;
                }
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("enuType", ptenumainAdd.getEnutype());
                paramMap.put("enuName", ptenumainAdd.getEnuname());
                sql = "INSERT INTO ptenumain  (enutype, enuname)" +
                        "VALUES(:enuType,:enuName)";
                skylineJdbc.update(sql, paramMap);
                successMessage("添加成功!...");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVDlgViewForTreeAdd').hide();");
            }else if("treeUpd".equals(addFlag)){
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("enuType", ptenumainUpd.getEnutype());
                paramMap.put("enuName", ptenumainUpd.getEnuname());
                sql = "UPDATE ptenumain  SET enuname =:enuName WHERE enutype =:enuType";
                skylineJdbc.update(sql, paramMap);
                successMessage("修改成功!...");
            }else if("treeDel".equals(addFlag)){
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("enuType", ptenumainDel.getEnutype());
//                删除枚举元素
                sqlD = "DELETE ptenudetail  WHERE enutype =:enuType";
                skylineJdbc.update(sqlD, paramMap);
//                删除枚举类型
                sql = "DELETE ptenumain WHERE enutype =:enuType";
                skylineJdbc.update(sql, paramMap);
                successMessage("删除成功!...");
            }
        } catch (Exception e) {
            logger.error("编辑数据时出现错误。", e);
            MessageUtil.addWarn("编辑数据时出现错误。" + e.getMessage());
        }
        init();
    }

    public void selectRecordAction(String strSubmitTypePara,Ptenudetail menudetailSelectedPara){
        enumId = selectedNode.getData().toString();
        enumName = enumId.substring(0,enumId.lastIndexOf("("));
        enumId = enumId.substring(enumId.lastIndexOf("(")+1,enumId.lastIndexOf(")"));
        try {
            if("enuItemDel".equals(strSubmitTypePara)){
                ptenudetailDel =(Ptenudetail) BeanUtils.cloneBean(menudetailSelectedPara);
            }else if("enuItemUpd".equals(strSubmitTypePara)){
                ptenudetailUpd =(Ptenudetail) BeanUtils.cloneBean(menudetailSelectedPara);
            }else if("enuItemAdd".equals(strSubmitTypePara)){
                ptenudetailAdd = new Ptenudetail();
                ptenudetailAdd.setEnutype(enumId);
            }
            strSubmitType = strSubmitTypePara;
        } catch (Exception e) {
            MessageUtil.addError(e.getMessage());
        }
    }

    //数据提交
    public void submitThisRecordAction(){
        try{
            //新增枚举
            if("enuItemAdd".equals(strSubmitType)){
                if(hasEquEnuItem(ptenudetailAdd.getEnutype(),ptenudetailAdd.getEnuitemvalue())){
                    addMessage("该枚举元素已存在，请查证后再操作...");
                    return;
                }
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("enuType", ptenudetailAdd.getEnutype());
                paramMap.put("enuItemValue", ptenudetailAdd.getEnuitemvalue());
                paramMap.put("enuItemLabel", ptenudetailAdd.getEnuitemlabel());
                paramMap.put("enuItemDesc", ptenudetailAdd.getEnuitemdesc());
                paramMap.put("enuDispno", ptenudetailAdd.getDispno());
                sql = "INSERT INTO ptenudetail  (enutype,enuitemvalue,enuitemlabel,enuitemdesc,dispno)" +
                        "VALUES(:enuType,:enuItemValue,:enuItemLabel,:enuItemDesc,:enuDispno)";
                skylineJdbc.update(sql, paramMap);
                successMessage("添加成功!...");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVDlgRecordForAdd').hide();");
                //修改
            } else if("enuItemUpd".equals(strSubmitType)){
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("enuType", ptenudetailUpd.getEnutype());
                paramMap.put("enuItemValue", ptenudetailUpd.getEnuitemvalue());
                paramMap.put("enuItemLabel", ptenudetailUpd.getEnuitemlabel());
                paramMap.put("enuItemDesc", ptenudetailUpd.getEnuitemdesc());
                paramMap.put("enuDispno", ptenudetailUpd.getDispno());
                sql = "UPDATE ptenudetail SET enuitemlabel =:enuItemLabel,enuitemdesc =:enuItemDesc,dispno =:enuDispno WHERE enutype =:enuType and enuitemvalue =:enuItemValue ";
                skylineJdbc.update(sql, paramMap);
                successMessage("修改成功!...");
                //删除枚举元素
            } else if ("enuItemDel".equals(strSubmitType)){
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("enuType", ptenudetailDel.getEnutype());
                paramMap.put("enuItemValue", ptenudetailDel.getEnuitemvalue());
                sql = "DELETE ptenudetail WHERE enutype =:enuType and enuitemvalue =:enuItemValue ";
                skylineJdbc.update(sql, paramMap);
                successMessage("删除成功!...");
            }
        } catch (Exception e) {
            logger.error("编辑数据时出现错误。", e);
            MessageUtil.addWarn("编辑数据时出现错误。" + e.getMessage());
        }
        onQuery();
    }

    //    新增枚举类型时用---判断是否用相同ID
    public boolean hasEquEnu(String enuId) {
        boolean childFlag = false;
        for (Ptenumain thrmenumain:enumainList) {
            if (enuId.equals(thrmenumain.getEnutype())) {
                childFlag = true;
                break;
            }
        }
        return childFlag;
    }
    //    新增枚举类型时用---判断是否用相同ID
    public boolean hasEquEnuItem(String enuId,String enuItem) {
        boolean childFlag = false;
        for (Ptenudetail thrmenudetail:enudetailList) {
            if (enuId.equals(thrmenudetail.getEnutype()) && enuItem.equals(thrmenudetail.getEnuitemvalue())) {
                childFlag = true;
                break;
            }
        }
        return childFlag;
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"警告",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
    }
    public void successMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"通知",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
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

    public NamedParameterJdbcTemplate getSkylineJdbc() {
        return skylineJdbc;
    }

    public void setSkylineJdbc(NamedParameterJdbcTemplate skylineJdbc) {
        this.skylineJdbc = skylineJdbc;
    }

    public Ptenumain getPtenumainAdd() {
        return ptenumainAdd;
    }

    public void setPtenumainAdd(Ptenumain ptenumainAdd) {
        this.ptenumainAdd = ptenumainAdd;
    }

    public Ptenumain getPtenumainUpd() {
        return ptenumainUpd;
    }

    public void setPtenumainUpd(Ptenumain ptenumainUpd) {
        this.ptenumainUpd = ptenumainUpd;
    }

    public Ptenumain getPtenumainDel() {
        return ptenumainDel;
    }

    public void setPtenumainDel(Ptenumain ptenumainDel) {
        this.ptenumainDel = ptenumainDel;
    }

    public Ptenudetail getPtenudetailAdd() {
        return ptenudetailAdd;
    }

    public void setPtenudetailAdd(Ptenudetail ptenudetailAdd) {
        this.ptenudetailAdd = ptenudetailAdd;
    }

    public Ptenudetail getPtenudetailUpd() {
        return ptenudetailUpd;
    }

    public void setPtenudetailUpd(Ptenudetail ptenudetailUpd) {
        this.ptenudetailUpd = ptenudetailUpd;
    }

    public Ptenudetail getPtenudetailDel() {
        return ptenudetailDel;
    }

    public void setPtenudetailDel(Ptenudetail ptenudetailDel) {
        this.ptenudetailDel = ptenudetailDel;
    }

    public List<Ptenudetail> getEnudetailList() {
        return enudetailList;
    }

    public void setEnudetailList(List<Ptenudetail> enudetailList) {
        this.enudetailList = enudetailList;
    }

    public List<Ptenumain> getEnumainList() {
        return enumainList;
    }

    public void setEnumainList(List<Ptenumain> enumainList) {
        this.enumainList = enumainList;
    }

    public List<SelectItem> getTreeEnus() {
        return treeEnus;
    }

    public void setTreeEnus(List<SelectItem> treeEnus) {
        this.treeEnus = treeEnus;
    }
}

