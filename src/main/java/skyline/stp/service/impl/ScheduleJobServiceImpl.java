package skyline.stp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skyline.security.OperManager;
import skyline.service.PlatformService;
import skyline.stp.common.enums.EnumOperateType;
import skyline.stp.common.enums.EnumScheduleType;
import skyline.stp.common.utils.ScheduleUtils;
import skyline.stp.repository.dao.StpScheduleJobMapper;
import skyline.stp.repository.dao.StpScheduleLogMapper;
import skyline.stp.repository.model.StpScheduleJob;
import skyline.stp.repository.model.StpScheduleJobExample;
import skyline.stp.repository.model.StpScheduleLog;
import skyline.stp.repository.model.StpScheduleLogExample;
import skyline.stp.service.ScheduleJobService;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    /** 调度工厂Bean */
//    @Autowired
//    private OperManager operManager;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private StpScheduleJobMapper stpScheduleJobMapper;

    @Autowired
    private StpScheduleLogMapper stpJoblogMapper;

    @Autowired
    private PlatformService platformService;

    private SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void initScheduleJob() {
        StpScheduleJobExample example=new StpScheduleJobExample();
        example.createCriteria().andDeletedflagEqualTo("0");
        List<StpScheduleJob> scheduleJobList = stpScheduleJobMapper.selectByExample(example);
        if (scheduleJobList.size()==0) {
            return;
        }
        for (StpScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler,
                    scheduleJob.getJobName(), scheduleJob.getJobGroup());
            //不存在，创建一个
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                //已存在，那么更新相应的定时设置
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Transactional
    public int insert(StpScheduleJob scheduleJobPara) {
        scheduleJobPara.setIsSync("0");
        scheduleJobPara.setDeletedflag("0");
        scheduleJobPara.setRecversion(1l);
        scheduleJobPara.setCreateTime(sf.format(new Date()));
        scheduleJobPara.setUpdateTime(sf.format(new Date()));
        scheduleJobPara.setScheduleType(EnumScheduleType.SCHEDULE_NORMAL.getCode());
        scheduleJobPara.setSucessNum(0l);
        scheduleJobPara.setFailNum(0l);
        int num=stpScheduleJobMapper.insert(scheduleJobPara);
        insertLog(EnumOperateType.OPERATE_ADD.getCode(),"",new StpScheduleLog(),scheduleJobPara);

        ScheduleUtils.createScheduleJob(scheduler, scheduleJobPara);
        return num;
    }

    @Transactional
    public void update(StpScheduleJob scheduleJobPara) {
        StpScheduleJob scheduleJobDB=stpScheduleJobMapper.selectByPrimaryKey(scheduleJobPara.getPkid());
        long recvcersion=scheduleJobPara.getRecversion();
        if (!scheduleJobDB.getRecversion().equals(recvcersion)){
            throw new RuntimeException("记录版本冲突");
        }else{
            scheduleJobPara.setRecversion(recvcersion+1);
            scheduleJobPara.setUpdateTime(sf.format(new Date()));
            scheduleJobPara.setScheduleType(EnumScheduleType.SCHEDULE_NORMAL.getCode());
            stpScheduleJobMapper.updateByPrimaryKey(scheduleJobPara);
            insertLog(EnumOperateType.OPERATE_UPD.getCode(),"",new StpScheduleLog(),scheduleJobPara);
        }
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJobPara);
    }

    @Transactional
    public void delUpdate(StpScheduleJob scheduleJobPara) {
        //数据库直接更新即可
        StpScheduleJob scheduleJobDB=stpScheduleJobMapper.selectByPrimaryKey(scheduleJobPara.getPkid());
        long recvcersion=scheduleJobPara.getRecversion();
        if (!scheduleJobDB.getRecversion().equals(recvcersion)){
            throw new RuntimeException("记录版本冲突");
        }else{
            scheduleJobPara.setRecversion(recvcersion+1);
            scheduleJobPara.setUpdateTime(sf.format(new Date()));
            scheduleJobPara.setScheduleType(EnumScheduleType.SCHEDULE_NORMAL.getCode());
            stpScheduleJobMapper.updateByPrimaryKey(scheduleJobPara);
            insertLog(EnumOperateType.OPERATE_DEL.getCode(),"",new StpScheduleLog(),scheduleJobPara);
        }
        //先删除
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJobPara.getJobName(),
                scheduleJobPara.getJobGroup());
        //再创建
        ScheduleUtils.createScheduleJob(scheduler, scheduleJobPara);
    }

    @Transactional
    public void delete(String scheduleJobIdPara) {
        StpScheduleJob scheduleJob = stpScheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        //删除运行的任务
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(),
                scheduleJob.getJobGroup());
        //删除数据
//        stpScheduleJobMapper.deleteByPrimaryKey(scheduleJobIdPara);
        scheduleJob.setDeletedflag("1");
        scheduleJob.setRecversion(scheduleJob.getRecversion()+1);
        scheduleJob.setUpdateTime(sf.format(new Date()));
        stpScheduleJobMapper.updateByPrimaryKey(scheduleJob);
        insertLog(EnumOperateType.OPERATE_DEL.getCode(),"",new StpScheduleLog(),scheduleJob);
    }

    @Transactional
    public void runOnce(String scheduleJobIdPara) {
        StpScheduleJob scheduleJob = stpScheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        scheduleJob.setScheduleType(EnumScheduleType.SCHEDULE_RUN_ONCE.getCode());
        stpScheduleJobMapper.updateByPrimaryKey(scheduleJob);

        insertLog(EnumOperateType.OPERATE_RUN_ONCE.getCode(), "", new StpScheduleLog(), scheduleJob);

        ScheduleUtils.runOnce(scheduler,scheduleJob);
    }

    public void pauseJob(String scheduleJobIdPara) {
        StpScheduleJob StpScheduleJob = stpScheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        ScheduleUtils.pauseJob(scheduler, StpScheduleJob.getJobName(), StpScheduleJob.getJobGroup());
        //演示数据库就不更新了
    }

    public void resumeJob(String scheduleJobIdPara) {
        StpScheduleJob StpScheduleJob = stpScheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        ScheduleUtils.resumeJob(scheduler, StpScheduleJob.getJobName(), StpScheduleJob.getJobGroup());
        //演示数据库就不更新了
    }

    public StpScheduleJob get(String scheduleJobIdPara) {
        StpScheduleJob StpScheduleJob = stpScheduleJobMapper.selectByPrimaryKey(scheduleJobIdPara);
        return StpScheduleJob;
    }


    public List<StpScheduleJob> queryList() {
        StpScheduleJobExample example=new StpScheduleJobExample();
        example.createCriteria().andDeletedflagEqualTo("0");
        example.setOrderByClause("SPRING_ID,JOB_METHOD");
        List<StpScheduleJob> scheduleJobs = stpScheduleJobMapper.selectByExample(example);
        try {
            for (StpScheduleJob item : scheduleJobs) {
                JobKey jobKey = ScheduleUtils.getJobKey(item.getJobName(), item.getJobGroup());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (triggers.size()==0) {
                    continue;
                }

                //这里一个任务可以有多个触发器， 但是我们一个任务对应一个触发器，所以只取第一个即可，清晰明了
                Trigger trigger = triggers.iterator().next();

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                item.setJobStatus(triggerState.name());
                item.setJobTrigger(trigger.getKey().getName());

                /*if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    item.setCronExpression(cronExpression);
                }*/
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return scheduleJobs;
    }

    public List<StpScheduleJob> queryExecutingJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<StpScheduleJob> jobList = new ArrayList<StpScheduleJob>(executingJobs.size());
            StpScheduleJob job=null;
            for (JobExecutionContext executingJob : executingJobs) {
                JobDetail jobDetail = executingJob.getJobDetail();
                String scheduleJobPkid=(String)jobDetail.getJobDataMap().get(StpScheduleJob.JOB_PARAM_KEY);
                job=get(scheduleJobPkid);

                JobKey jobKey = jobDetail.getKey();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());

                Trigger trigger = executingJob.getTrigger();
                job.setJobTrigger(trigger.getKey().getName());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }else{
                    job.setCronExpression("");
                }
                jobList.add(job);
            }
            return jobList;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExistsExecutingJob(StpScheduleJob scheduleJobPara) {
        try {
            StpScheduleJob scheduleJob=stpScheduleJobMapper.selectByPrimaryKey(scheduleJobPara.getPkid());
            JobKey templeteJobKey=JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            for (JobExecutionContext executingJob : executingJobs) {
                JobDetail jobDetail = executingJob.getJobDetail();
                if (jobDetail.getKey().equals(templeteJobKey)){
                    return true;
                }
            }
            return false;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExistsScheduleJob(StpScheduleJob scheduleJobPara){
        StpScheduleJobExample example=new StpScheduleJobExample();
        example.createCriteria()
                .andJobNameEqualTo(scheduleJobPara.getJobName())
                .andJobGroupEqualTo(scheduleJobPara.getJobGroup());
        List<StpScheduleJob> scheduleJobList=stpScheduleJobMapper.selectByExample(example);
        if(scheduleJobList.size()!=0){
            return true;
        }else{
            return false;
        }
    }

    public void insertLog(String typePara,String jobDescPara,StpScheduleLog logPara,StpScheduleJob scheduleJobPara) {
        String userid;
        String username;
//        if (!operManager.isLogined()) {
//            userid = "9999";
//            username = "系统管理员";
//        } else {
//            userid = operManager.getOperInfo().getOperId();
//            username = operManager.getOperInfo().getOperName();
//        }
        userid = "9999";
        username = "系统管理员";

        logPara.setJobtime(sf.format(new Date()));
        logPara.setJobuserid(userid);
        logPara.setJobusername(username);

        logPara.setTablename("STP_SCHEDULE_JOB");
        logPara.setType(typePara);
        logPara.setRowpkid(scheduleJobPara.getPkid());
        logPara.setJobcode("");
        logPara.setJobname(scheduleJobPara.getJobName());
        logPara.setJobgroup(scheduleJobPara.getJobGroup());
        logPara.setJobdesc(jobDescPara);
        stpJoblogMapper.insert(logPara);
    }

    public List<StpScheduleLog> quaryJobLogList(StpScheduleJob scheduleJobPara){
        StpScheduleLogExample example=new StpScheduleLogExample();
        StpScheduleLogExample.Criteria criteria=example.createCriteria();
        criteria.andRowpkidEqualTo(scheduleJobPara.getPkid());

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        criteria.andJobtimeLike(sf.format(new Date())+"%");

        List<String> scheduleTypeList=new ArrayList<String>();
        for (EnumScheduleType item:EnumScheduleType.values()){
            scheduleTypeList.add(item.getCode());
        }
        criteria.andTypeIn(scheduleTypeList);

        example.setOrderByClause("JOBTIME desc");

        return stpJoblogMapper.selectByExample(example);
    }

    public List<StpScheduleLog> quaryJobLogListForQry(StpScheduleLog stpScheduleLogPara){
        StpScheduleLogExample example=new StpScheduleLogExample();
        StpScheduleLogExample.Criteria criteria=example.createCriteria();
        criteria.andRowpkidEqualTo(stpScheduleLogPara.getPkid());

        criteria.andJobtimeLike("%"+stpScheduleLogPara.getJobtime()+"%");

        List<String> scheduleTypeList=new ArrayList<String>();
        for (EnumScheduleType item:EnumScheduleType.values()){
            scheduleTypeList.add(item.getCode());
        }
        criteria.andTypeIn(scheduleTypeList);

        example.setOrderByClause("JOBTIME desc");

        return stpJoblogMapper.selectByExample(example);
    }

    public void updateScheduleRelated(String descPara,StpScheduleJob scheduleJobPara){
        stpScheduleJobMapper.updateByPrimaryKey(scheduleJobPara);
        insertLog(scheduleJobPara.getScheduleType(),descPara,new StpScheduleLog(),scheduleJobPara);
    }
}
