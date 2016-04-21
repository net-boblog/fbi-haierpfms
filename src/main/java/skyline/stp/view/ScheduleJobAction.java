package skyline.stp.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.common.PropertyManager;
import skyline.common.primefaces.MessageUtil;
import skyline.stp.common.enums.EnumOperateType;
import skyline.stp.common.enums.EnumScheduleType;
import skyline.stp.common.utils.SpringUtils;
import skyline.stp.repository.model.StpScheduleJob;
import skyline.stp.repository.model.StpScheduleLog;
import skyline.stp.service.ScheduleJobService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

@ManagedBean
@ViewScoped
public class ScheduleJobAction implements Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobAction.class);

    @ManagedProperty(value = "#{scheduleJobServiceImpl}")
    private ScheduleJobService scheduleJobService;

    private List<StpScheduleJob> scheduleJobList;
    private List<StpScheduleJob> executingJobList;

    private String strDialogHeader;
    private String isDisabled;
    private String isResetBtnRender;
    private EnumOperateType enumOperateType;
    private StpScheduleJob scheduleJobTemplate;
    private StpScheduleJob selectRecord;
    private List<StpScheduleLog> jobLogList;
    private StpScheduleLog stpScheduleLogQry;
    private SelectItem[] beanNameItems;
    private EnumScheduleType scheduleType;
    private String paramPkid;

    @PostConstruct
    public void init() {
        try {
            stpScheduleLogQry = new StpScheduleLog();
            paramPkid = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pkid");
            /*if (!StringUtils.isEmpty(paramPkid)){
                selectRecord=scheduleJobService.get(paramPkid);
                jobLogList=scheduleJobService.quaryJobLogList(selectRecord);
            }*/

            scheduleJobList=new ArrayList<StpScheduleJob>();
            executingJobList=new ArrayList<StpScheduleJob>();
            scheduleJobTemplate=new StpScheduleJob();
        }catch (Exception e){
            logger.error("页面初始化错误", e);
        }
    }

    public SelectItem[] initSpringIdData() {
        String srcPath = PropertyManager.getProperty("quartz.spring.scan.path");
        if(srcPath!=null) {
            String[] pathList = srcPath.split(",");
            List<String> beanNameList = SpringUtils.getSpecifiedPathBeanNames(pathList);
            beanNameItems = new SelectItem[beanNameList.size() + 1];

            beanNameItems[0] = new SelectItem("", "请选择");
            for (int i = 0; i < beanNameList.size(); i++) {
                beanNameItems[i + 1] = new SelectItem(beanNameList.get(i), beanNameList.get(i));
            }
        }
        return beanNameItems;
    }

    /**
     * 任务列表页
     * @return
     */
    public List<StpScheduleJob> initScheduleJobList() {
        try {
            scheduleJobList = scheduleJobService.queryList();
        }catch (Exception e){
            logger.error("查询任务列表出错",e);
            MessageUtil.addError("查询任务列表出错" + e.toString());
        }
        return scheduleJobList;
    }

    /**
     * 正在运行任务列表页
     * @return
     */
    public List<StpScheduleJob> initExecutingJobList() {
        try {
            executingJobList = scheduleJobService.queryExecutingJobList();
        }catch (Exception e){
            logger.error("查询运行中任务出错",e);
            MessageUtil.addError("查询运行中任务出错"+e.toString());
        }
        return executingJobList;
    }

    public void addRecordAction(){
        enumOperateType = EnumOperateType.OPERATE_ADD;
        strDialogHeader= enumOperateType.getTitle();
        scheduleJobTemplate=new StpScheduleJob();
        isDisabled="false";
        isResetBtnRender="true";
    }

    public void selectRecordAction(String strSubmitTypePara,StpScheduleJob scheduleJobPara){
        try {
            scheduleJobTemplate =(StpScheduleJob) BeanUtils.cloneBean(scheduleJobPara);
            enumOperateType = EnumOperateType.valueOf(strSubmitTypePara);
            strDialogHeader= enumOperateType.getTitle();
//            if (operateType.equals(OperateType.OPERATE_UPD)||operateType.equals(OperateType.OPERATE_DEL_CREATE)){
            if (enumOperateType.equals(EnumOperateType.OPERATE_UPD)){
                isDisabled="false";
                isResetBtnRender="true";
            }else{
                isDisabled="true";
                isResetBtnRender="false";
            }
        } catch (Exception e) {
            logger.error(e.toString());
            MessageUtil.addError(e.getMessage());
        }
    }

    public void doShowLog(StpScheduleJob scheduleJobPara){
        try {
            this.selectRecord = scheduleJobPara;
            //jobLogList=scheduleJobService.quaryJobLogList(selectRecord);
        } catch (Exception e) {
            logger.error(e.toString());
            MessageUtil.addError(e.getMessage());
        }
    }


    public boolean submitPreCheck(StpScheduleJob scheduleJobPara){
        if(StringUtils.isEmpty(scheduleJobPara.getJobName())||
                StringUtils.isEmpty(StringUtils.trim(scheduleJobPara.getJobName()))){
            MessageUtil.addError("名称不能为空！");
            return false;
        }else if(StringUtils.isEmpty(scheduleJobPara.getJobGroup())||
                StringUtils.isEmpty(StringUtils.trim(scheduleJobPara.getJobGroup()))){
            MessageUtil.addError("工作属组不能为空！");
            return false;
        }else if(StringUtils.isEmpty(scheduleJobPara.getCronExpression())||
                StringUtils.isEmpty(StringUtils.trim(scheduleJobPara.getCronExpression()))){
            MessageUtil.addError("时间表达式不能为空！");
            return false;
        }/*else if((StringUtils.isEmpty(scheduleJobPara.getJobAction())||
                StringUtils.isEmpty(StringUtils.trim(scheduleJobPara.getJobAction())))&&
                (StringUtils.isEmpty(scheduleJobPara.getSpringId())||
                        StringUtils.isEmpty(StringUtils.trim(scheduleJobPara.getSpringId())))){
            MessageUtil.addError("动作类和springId不能同时为空！");
            isErr=true;
        }*/
        else if(StringUtils.isEmpty(scheduleJobPara.getSpringId())||
                StringUtils.isEmpty(StringUtils.trim(scheduleJobPara.getSpringId()))){
            MessageUtil.addError("springId不能为空！");
            return false;
        }else if(StringUtils.isEmpty(scheduleJobPara.getJobMethod())||
                StringUtils.isEmpty(StringUtils.trim(scheduleJobPara.getJobMethod()))){
            MessageUtil.addError("动作函数不能为空！");
            return false;
        }
        return true;
    }

    public void submitThisRecordAction(){
        try{
            if(EnumOperateType.OPERATE_ADD.equals(enumOperateType)){
                if (!submitPreCheck(scheduleJobTemplate)){
                    return;
                }
                if(scheduleJobService.isExistsScheduleJob(scheduleJobTemplate)){
                    MessageUtil.addError("操作失败，系统中当前已存在该任务！");
                    return;
                }else{
                    scheduleJobService.insert(scheduleJobTemplate);
                }
            }else if(EnumOperateType.OPERATE_UPD.equals(enumOperateType)){
                if (!submitPreCheck(scheduleJobTemplate)){
                    return;
                }
                if(scheduleJobService.isExistsExecutingJob(scheduleJobTemplate)){
                    MessageUtil.addError("操作失败，当前存在正在执行的任务，无法更新！");
                    return;
                }else{
                    scheduleJobService.update(scheduleJobTemplate);
                }
            }/*else if(OperateType.OPERATE_DEL_CREATE.equals(operateType)){
                scheduleJobService.delUpdate(scheduleJobTemplate);
            }*/else if(EnumOperateType.OPERATE_DEL.equals(enumOperateType)){
                scheduleJobService.delete(scheduleJobTemplate.getPkid());
            }else if(EnumOperateType.OPERATE_PAUSE.equals(enumOperateType)){
                scheduleJobService.pauseJob(scheduleJobTemplate.getPkid());
            }else if(EnumOperateType.OPERATE_RESUME.equals(enumOperateType)){
                scheduleJobService.resumeJob(scheduleJobTemplate.getPkid());
            }else if(EnumOperateType.OPERATE_RUN_ONCE.equals(enumOperateType)){
                scheduleJobService.runOnce(scheduleJobTemplate.getPkid());
            }
            initScheduleJobList();
            initExecutingJobList();
            MessageUtil.addInfo(enumOperateType.getTitle()+"成功");
        }catch (Exception e){
            logger.error("提交信息失败",e);
            MessageUtil.addError(enumOperateType.getTitle()+"失败"+e.toString());
        }
    }

    public void onQuery() {
        try {
            StpScheduleLog stpScheduleLogTmp=new StpScheduleLog();
            stpScheduleLogTmp.setPkid(selectRecord.getPkid());
            stpScheduleLogTmp.setJobtime(stpScheduleLogQry.getJobtime());
            jobLogList=scheduleJobService.quaryJobLogListForQry(stpScheduleLogTmp);
        } catch (Exception e) {
            logger.error("获取记录时出错", e);
            MessageUtil.addError("获取记录时出错。" + e.getMessage() + e.getCause());
        }
    }

    public ScheduleJobService getScheduleJobService() {
        return scheduleJobService;
    }

    public void setScheduleJobService(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    public List<StpScheduleJob> getScheduleJobList() {
        return scheduleJobList;
    }

    public void setScheduleJobList(List<StpScheduleJob> scheduleJobList) {
        this.scheduleJobList = scheduleJobList;
    }

    public List<StpScheduleJob> getExecutingJobList() {
        return executingJobList;
    }

    public void setExecutingJobList(List<StpScheduleJob> executingJobList) {
        this.executingJobList = executingJobList;
    }

    public StpScheduleJob getScheduleJobTemplate() {
        return scheduleJobTemplate;
    }

    public void setScheduleJobTemplate(StpScheduleJob scheduleJobTemplate) {
        this.scheduleJobTemplate = scheduleJobTemplate;
    }

    public String getStrDialogHeader() {
        return strDialogHeader;
    }

    public void setStrDialogHeader(String strDialogHeader) {
        this.strDialogHeader = strDialogHeader;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getIsResetBtnRender() {
        return isResetBtnRender;
    }

    public void setIsResetBtnRender(String isResetBtnRender) {
        this.isResetBtnRender = isResetBtnRender;
    }

    public StpScheduleJob getSelectRecord() {
        return selectRecord;
    }

    public void setSelectRecord(StpScheduleJob selectRecord) {
        this.selectRecord = selectRecord;
    }

    public List<StpScheduleLog> getJobLogList() {
        return jobLogList;
    }

    public void setJobLogList(List<StpScheduleLog> jobLogList) {
        this.jobLogList = jobLogList;
    }

    public EnumOperateType getEnumOperateType() {
        return enumOperateType;
    }

    public void setEnumOperateType(EnumOperateType enumOperateType) {
        this.enumOperateType = enumOperateType;
    }

    public SelectItem[] getBeanNameItems() {
        return beanNameItems;
    }

    public void setBeanNameItems(SelectItem[] beanNameItems) {
        this.beanNameItems = beanNameItems;
    }

    public EnumScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(EnumScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public StpScheduleLog getStpScheduleLogQry() {
        return stpScheduleLogQry;
    }

    public void setStpScheduleLogQry(StpScheduleLog stpScheduleLogQry) {
        this.stpScheduleLogQry = stpScheduleLogQry;
    }
}
