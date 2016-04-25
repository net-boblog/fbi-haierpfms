package skyline.view;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import skyline.common.MD5Helper;
import skyline.common.primefaces.MessageUtil;
import skyline.repository.model.Ptoper;
import skyline.repository.model.Ptrole;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihe
 * on 2016/1/13.09:18
 */
@ManagedBean
@ViewScoped
public class OperRoleMgmtView {

    private String sql;
    private String sqlD;
    private TreeNode root1;
    private TreeNode pnode;
    private TreeNode[] selectedNode2;
    private List<Ptoper> operList;
    private Ptoper ptoperQry;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ManagedProperty(value = "#{skylineJdbc}")
    private NamedParameterJdbcTemplate skylineJdbc;

    @PostConstruct
    public void init(){
        ptoperQry = new Ptoper();
    }

    //    �û��б��ѯ
    public void onQuery(){
        try {
            Map<String,Object> paramMap = new HashMap<String,Object>();
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
            logger.error("��ѯ����ʱ���ִ���", e);
            MessageUtil.addWarn("��ѯ����ʱ���ִ���" + e.getMessage());
        }
    }

    private List<Ptrole> operRoleList;
    private List<Ptrole> roleList;
    private String operid ="";
    public void selectRecordAction(Ptoper ptoperSelectedPara){
        try {
            operid = ptoperSelectedPara.getOperid();
            root1 = new DefaultTreeNode("Root", null);
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("operid",operid);
            //��øý�ɫȨ��
            sql = "SELECT t.roleid,t.roledesc,t.status FROM ptoperrole j LEFT JOIN ptrole t  ON j.roleid = t.roleid WHERE t.status ='1' AND j.operid =:operid ORDER BY t.roleid ASC ";
            operRoleList = skylineJdbc.query(sql,paramMap, new BeanPropertyRowMapper<Ptrole>(Ptrole.class));
            //���ȫ��Ȩ��
            sqlD = "SELECT t.roleid,t.roledesc,t.status FROM ptrole t WHERE t.status ='1' ORDER BY t.roleid ASC";
            roleList = skylineJdbc.query(sqlD,new BeanPropertyRowMapper<Ptrole>(Ptrole.class));
            //����Ȩ����
            for(Ptrole pAll:roleList){
                pnode  = new DefaultTreeNode(pAll.getRoledesc()+"("+pAll.getRoleid()+")", root1);
                for(Ptrole pSig:operRoleList){
                    if(pSig.getRoleid().equals(pAll.getRoleid())){
                        pnode.setSelected(true);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("��ѯ����ʱ���ִ���", e);
            MessageUtil.addWarn("��ѯ����ʱ���ִ���" + e.getMessage());
        }
    }
    public  void  submitThisRecordAction(String rrrr){
        try {
            Map<String, Object> paramMap = new HashMap<String,Object>();
            TreeNode[] nodes = selectedNode2;
            paramMap.put("operid",operid);
            sql = "DELETE ptoperrole j WHERE j.operid =:operid ";
            skylineJdbc.update(sql, paramMap);
            if(nodes != null && nodes.length > 0) {
                for (TreeNode node : nodes) {
                    Map<String,Object> paramMapT = new HashMap<String,Object>();
                    int subS = node.getData().toString().lastIndexOf("(");
                    int subE = node.getData().toString().lastIndexOf(")");
                    paramMapT.put("operid",operid);
                    paramMapT.put("roleid",node.getData().toString().substring(subS+1,subE));
                    sqlD = "INSERT INTO ptoperrole J (J.roleid, J.operid) VALUES (:roleid, :operid) ";
                    skylineJdbc.update(sqlD, paramMapT);
                }
            }
            successMessage("��Ȩ�ɹ�!...");
        } catch (Exception e){
            logger.error("�༭����ʱ���ִ���", e);
            MessageUtil.addWarn("�༭����ʱ���ִ���" + e.getMessage());
        }
        onQuery();
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"����",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
    }
    public void successMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"֪ͨ",summary);
        FacesContext.getCurrentInstance().addMessage("infoMsg", message);
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

    public Ptoper getPtoperQry() {
        return ptoperQry;
    }

    public void setPtoperQry(Ptoper ptoperQry) {
        this.ptoperQry = ptoperQry;
    }
}
