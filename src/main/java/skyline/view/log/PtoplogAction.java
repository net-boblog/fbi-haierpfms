package skyline.view.log;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import skyline.common.primefaces.MessageUtil;
import skyline.repository.model.Ptoplog;
import skyline.security.OperManager;
import skyline.service.PlatformService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: zhanrui
 * Date: 13-2-4
 * Time: ����1:44
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class PtoplogAction implements Serializable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<SelectItem> branchList;
    private String branchId;
    private String operId;
    private String startDate;
    private String endDate;

    private List<Ptoplog> detlList;
    private List<Ptoplog> filteredDetlList;

    @ManagedProperty(value = "#{operManager}")
    private OperManager operManager;
    @ManagedProperty(value = "#{platformService}")
    private PlatformService platformService;
    @ManagedProperty(value = "#{skylineJdbc}")
    private NamedParameterJdbcTemplate skylineJdbc;


    @PostConstruct
    public void init() {
        String operid = operManager.getOperInfo().getOperId();
        String branchid = operManager.getOperInfo().getPtdept().getDeptid();

        this.branchList = platformService.selectBranchList(branchid);

        this.startDate = new DateTime().dayOfMonth().withMinimumValue().toString("yyyy-MM-dd");
        this.endDate = new DateTime().toString("yyyy-MM-dd");
        detlList = new ArrayList<Ptoplog>();
    }

    public String onQuery() {
        try {
            long sn = platformService.obtainSeqNo("opLogSn");
            Ptoplog oplog = new Ptoplog();
            oplog.setActionId("Ptoplog_onQuery");
            oplog.setActionName("ϵͳ������־��ѯ");
            oplog.setOpDataBranchid(this.branchId);
            oplog.setOpDataStartdate(this.startDate);
            oplog.setOpDataEnddate(this.endDate);
            oplog.setOpLog("���β�����ˮ��:" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + StringUtils.leftPad("" + sn, 12, "0"));
            platformService.insertNewOperationLog(oplog);

            Map<String,Object> paramMap = new HashMap<String,Object>();
            List<String> branchStrList = platformService.selectBranchLevelList(branchId);
            paramMap.put("branchStrList", branchStrList);
            paramMap.put("startDate", this.startDate);
            paramMap.put("endDate", this.endDate);
            String sql = "select * from ptoplog where oper_branchid in (:branchStrList)" +
                    " and to_char(op_date,'yyyy-mm-dd') >= :startDate and to_char(op_date,'yyyy-mm-dd') <= :endDate " +
                    " order by op_date desc, oper_branchid";
            detlList = skylineJdbc.query(sql, paramMap, new BeanPropertyRowMapper<Ptoplog>(Ptoplog.class));
        } catch (Exception e) {
            logger.error("��ѯ����ʱ���ִ���", e);
            MessageUtil.addWarn("��ѯ����ʱ���ִ���" + e.getMessage());
        }
        return null;
    }

    //=========================

    public List<SelectItem> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<SelectItem> branchList) {
        this.branchList = branchList;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Ptoplog> getDetlList() {
        return detlList;
    }

    public void setDetlList(List<Ptoplog> detlList) {
        this.detlList = detlList;
    }

    public OperManager getOperManager() {
        return operManager;
    }

    public void setOperManager(OperManager operManager) {
        this.operManager = operManager;
    }

    public PlatformService getPlatformService() {
        return platformService;
    }

    public void setPlatformService(PlatformService platformService) {
        this.platformService = platformService;
    }

    public List<Ptoplog> getFilteredDetlList() {
        return filteredDetlList;
    }

    public NamedParameterJdbcTemplate getSkylineJdbc() {
        return skylineJdbc;
    }

    public void setSkylineJdbc(NamedParameterJdbcTemplate skylineJdbc) {
        this.skylineJdbc = skylineJdbc;
    }

    public void setFilteredDetlList(List<Ptoplog> filteredDetlList) {
        this.filteredDetlList = filteredDetlList;
    }
}
