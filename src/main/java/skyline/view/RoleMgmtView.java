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
import skyline.repository.model.Ptmenu;
import skyline.repository.model.Ptrole;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class RoleMgmtView implements Serializable {

    private String sql ;
    private String sqlD ;
    private String sqlT ;
    private String strTreeSubmitType ;
    private String strRecordSubmitType ;
    private String roleId;
    private String roleName;

    private TreeNode root1;
    private TreeNode root2;
    private TreeNode pnode;
    private TreeNode cnode;

    private TreeNode selectedNode;
    private TreeNode[] selectedNode2;
    private List<Ptrole> roleList;
    private Ptrole ptroleUpd;
    private Ptrole ptroleDel;
    private Ptrole ptroleAdd;

    private Ptmenu ptmenuResDel;
    private List<Ptmenu> ptmenuResList;
    private List<Ptmenu> ptmenuList;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ManagedProperty(value = "#{skylineJdbc}")
    private NamedParameterJdbcTemplate skylineJdbc;

    @PostConstruct
    public void init() {
        root1 = new DefaultTreeNode("Root", null);
        try {
            sql = "SELECT * FROM PTROLE T WHERE T.STATUS = '1' ORDER BY T.ROLEID ASC";
            roleList = skylineJdbc.query(sql,new BeanPropertyRowMapper<Ptrole>(Ptrole.class));
        } catch (Exception e) {
            logger.error("��ѯ����ʱ���ִ���", e);
            MessageUtil.addWarn("��ѯ����ʱ���ִ���" + e.getMessage());
        }
        for(Ptrole ptroleT:roleList){
            pnode = new DefaultTreeNode(ptroleT.getRoledesc()+"("+ptroleT.getRoleid()+")", root1);
        }
        ptroleUpd = new Ptrole();
        ptroleDel = new Ptrole();
        ptroleAdd = new Ptrole();
    }

    //  ��ɫ��Ȩ��ѯ��
    public void onQuery(){
        try {
            roleId = selectedNode.getData().toString();
            roleId = roleId.substring(roleId.lastIndexOf("(")+1,roleId.lastIndexOf(")"));
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("roleId", roleId);
            sql = "SELECT M.* FROM PTROLERES R, PTMENU M WHERE R.RESID = 'm'||M.MENUID AND R.ROLEID =:roleId ORDER BY R.RESID ";
            ptmenuResList = skylineJdbc.query(sql,paramMap, new BeanPropertyRowMapper<Ptmenu>(Ptmenu.class));
        } catch (Exception e) {
            logger.error("��ѯ����ʱ���ִ���", e);
            MessageUtil.addWarn("��ѯ����ʱ���ִ���" + e.getMessage());
        }
    }

    //  ��ɫ��Ȩɾ�����ݸ��ơ������б�չʾ
    public void selectRecordAction(String strSubmitTypePara,Ptmenu ptroleResSelectedPara){
        try {
            if ("Del".equals(strSubmitTypePara)) {
                ptmenuResDel = (Ptmenu) BeanUtils.cloneBean(ptroleResSelectedPara);
            }else if("Add".equals(strSubmitTypePara)){
                onQuery();
                root2 = new DefaultTreeNode("Root", null);
                try {
                    sqlT ="SELECT * FROM PTMENU T ORDER BY T.LEVELIDX ASC ";
                    ptmenuList = skylineJdbc.query(sqlT,new BeanPropertyRowMapper<Ptmenu>(Ptmenu.class));
                } catch (Exception e) {
                    logger.error("��ѯ����ʱ���ִ���", e);
                    MessageUtil.addWarn("��ѯ����ʱ���ִ���" + e.getMessage());
                }
                for(Ptmenu p:ptmenuList){
                    if("0".equals(p.getParentmenuid())){
                        pnode  = new DefaultTreeNode(p.getMenuid()+"_"+p.getMenulabel(), root2);
                        for(Ptmenu pc:ptmenuResList) {
                            if (pc.getMenuid().equals(p.getMenuid())) {
                                pnode.setSelected(true);
                            }
                        }
                        treeRecursion(p.getMenuid(),pnode);
                    }
                }
            }
            strRecordSubmitType = strSubmitTypePara;
        }catch (Exception e){
            MessageUtil.addError(e.getMessage());
        }
    }

    public void treeRecursion(String treeid,TreeNode parentNode){
        for(Ptmenu pr:ptmenuList) {
            if (treeid.equals(pr.getParentmenuid())) {
                TreeNode cnode = new DefaultTreeNode(pr.getMenuid()+"_"+pr.getMenulabel(), parentNode);
                for(Ptmenu prc:ptmenuResList) {
                    if (prc.getMenuid().equals(pr.getMenuid())) {
                        cnode.setSelected(true);
                    }
                }
                if(hasChild(pr.getMenuid())){
                    treeRecursion(pr.getMenuid(),cnode);
                }
            }
        }
    }
    //    �ж����˵��Ƿ����ӽڵ�
    public boolean hasChild(String treeid){
        boolean childFlag = false;
        for(Ptmenu pre:ptmenuList) {
            if (treeid.equals(pre.getParentmenuid())) {
                childFlag = true;
                break;
            }
        }
        return childFlag;
    }

    //��ɫ��Ȩɾ������������
    public void submitThisRecordAction(TreeNode[] nodes){
        try {
            if ("Del".equals(strRecordSubmitType)) {
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("roleId", roleId);
                paramMap.put("resId", ptmenuResDel.getMenuid());
                sqlD = "delete PTROLERES j WHERE j.roleid =:roleId and j.resid = 'm'||:resId";
                skylineJdbc.update(sqlD, paramMap);
                successMessage("ɾ���ɹ�!...");
            } else if("Add".equals(strRecordSubmitType)){
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("roleId", roleId);
                sqlD = "delete PTROLERES j WHERE j.roleid =:roleId ";
                skylineJdbc.update(sqlD, paramMap);
                if(nodes != null && nodes.length > 0) {
                    for (TreeNode node : nodes) {
                        Map<String,Object> paramMapT = new HashMap<String,Object>();
                        int subP = node.getData().toString().indexOf("_");
                        paramMapT.put("roleId",roleId);
                        paramMapT.put("resId",node.getData().toString().substring(0,subP));
                        sqlD = "INSERT INTO PTROLERES J (J.RESID, J.ROLEID) VALUES ('m'||:resId, :roleId) ";
                        skylineJdbc.update(sqlD, paramMapT);
                    }
                }
                successMessage("�޸ĳɹ�!...");
            }
        }catch (Exception e){
            logger.error("�༭����ʱ���ִ���", e);
            MessageUtil.addWarn("�༭����ʱ���ִ���" + e.getMessage());
        }
        onQuery();
    }

    //��ɫ��������ɾ���ģ�
    public void selectTreeAction(String strSubmitTypePara){
        try {
            roleId = selectedNode.getData().toString();
            roleName = roleId.substring(0,roleId.lastIndexOf("("));
            roleId = roleId.substring(roleId.lastIndexOf("(")+1,roleId.lastIndexOf(")"));
            if("Upd".equals(strSubmitTypePara)){
                ptroleUpd.setRoleid(roleId);
                ptroleUpd.setRoledesc(roleName);
            }else if("Del".equals(strSubmitTypePara)){
                ptroleDel.setRoleid(roleId);
                ptroleDel.setRoledesc(roleName);
            }
            strTreeSubmitType = strSubmitTypePara;
        } catch (Exception e) {
            MessageUtil.addError(e.getMessage());
        }
    }

    public void submitThisTreeAction(){
        try {
            if("Upd".equals(strTreeSubmitType)) {
                if("".equals(ptroleUpd.getRoledesc())){
                    addMessage("��ɫ��������Ϊ�գ���ȷ�Ϻ��ٴθ���...");
                    return;
                }
                if(hasEquals("",ptroleUpd.getRoledesc())) {
                    addMessage("��ɫ�����Ѵ��ڣ���ȷ�Ϻ��ٴθ���...");
                    return;
                }
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("roleId", ptroleUpd.getRoleid());
                paramMap.put("roleDesc", ptroleUpd.getRoledesc());
                sqlD = "UPDATE ptrole j SET j.roledesc =:roleDesc WHERE j.roleid =:roleId";
                skylineJdbc.update(sqlD, paramMap);
                successMessage("�޸ĳɹ�!...");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVDlgViewForUpd').hide();");
            }else if ("Del".equals(strTreeSubmitType)){
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("roleId", ptroleDel.getRoleid());
//                ɾ����ɫ��Ȩ
                sqlT = "delete PTROLERES j  WHERE j.roleid =:roleId";
                skylineJdbc.update(sqlT, paramMap);
//                ɾ����ɫ
                sqlD = "delete ptrole j WHERE j.roleid =:roleId";
                skylineJdbc.update(sqlD, paramMap);
                successMessage("ɾ���ɹ�!...");
            }else if ("Add".equals(strTreeSubmitType)){
                if("".equals(ptroleAdd.getRoleid())||"".equals(ptroleAdd.getRoledesc())){
                    addMessage("������ɫID���ɫ��������Ϊ�գ���ȷ�Ϻ��ٴ����...");
                    return;
                }
                if(hasEquals(ptroleAdd.getRoleid(),ptroleAdd.getRoledesc())){
                    addMessage("������ɫID���ɫ�����Ѵ��ڣ���ȷ�Ϻ��ٴ����...");
                    return;
                }
                Map<String, Object> paramMap = new HashMap<String,Object>();
                paramMap.put("roleId", ptroleAdd.getRoleid());
                paramMap.put("roleDesc", ptroleAdd.getRoledesc());
                sqlD = "insert into ptrole j (j.roleid,j.roledesc,j.status) values (:roleId,:roleDesc,'1')";
                skylineJdbc.update(sqlD, paramMap);
                successMessage("��ӳɹ�!...");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('wVDlgViewForAdd').hide();");
            }
        }catch (Exception e){
            logger.error("�༭����ʱ���ִ���", e);
            MessageUtil.addWarn("�༭����ʱ���ִ���" + e.getMessage());
        }
        init();
    }
    //
    public boolean hasEquals(String objectId,String objectName){
        boolean objectFlag = false;
        for(Ptrole ptroleE:roleList){
            if(objectId.equals(ptroleE.getRoleid())||objectName.equals(ptroleE.getRoledesc())){
                objectFlag = true;
                break;
            }
        }
        return objectFlag;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"����",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
    }
    public void successMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"֪ͨ",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
    }
    public List<Ptrole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Ptrole> roleList) {
        this.roleList = roleList;
    }

    public List<Ptmenu> getPtmenuResList() {
        return ptmenuResList;
    }

    public void setPtmenuResList(List<Ptmenu> ptmenuResList) {
        this.ptmenuResList = ptmenuResList;
    }

    public NamedParameterJdbcTemplate getSkylineJdbc() {
        return skylineJdbc;
    }

    public void setSkylineJdbc(NamedParameterJdbcTemplate skylineJdbc) {
        this.skylineJdbc = skylineJdbc;
    }

    public Ptmenu getPtmenuResDel() {
        return ptmenuResDel;
    }

    public void setPtmenuResDel(Ptmenu ptmenuResDel) {
        this.ptmenuResDel = ptmenuResDel;
    }

    public Ptrole getPtroleUpd() {
        return ptroleUpd;
    }

    public void setPtroleUpd(Ptrole ptroleUpd) {
        this.ptroleUpd = ptroleUpd;
    }

    public Ptrole getPtroleDel() {
        return ptroleDel;
    }

    public void setPtroleDel(Ptrole ptroleDel) {
        this.ptroleDel = ptroleDel;
    }

    public Ptrole getPtroleAdd() {
        return ptroleAdd;
    }

    public void setPtroleAdd(Ptrole ptroleAdd) {
        this.ptroleAdd = ptroleAdd;
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

}
