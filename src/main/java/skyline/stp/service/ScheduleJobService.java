package skyline.stp.service;


import skyline.stp.repository.model.StpScheduleJob;
import skyline.stp.repository.model.StpScheduleLog;

import java.util.List;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

public interface ScheduleJobService {

    /**
     * 初始化定时任务
     */
    public void initScheduleJob();

    /**
     * 新增
     * 
     * @param scheduleJobPara
     * @return
     */
    public int insert(StpScheduleJob scheduleJobPara);

    /**
     * 直接修改 只能修改运行的时间，参数、同异步等无法修改
     * 
     * @param scheduleJobPara
     */
    public void update(StpScheduleJob scheduleJobPara);

    /**
     * 删除重新创建方式
     * 
     * @param scheduleJobPara
     */
    public void delUpdate(StpScheduleJob scheduleJobPara);

    /**
     * 删除
     * 
     * @param scheduleJobIdPara
     */
    public void delete(String scheduleJobIdPara);

    /**
     * 运行一次任务
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void runOnce(String scheduleJobIdPara);

    /**
     * 暂停任务
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void pauseJob(String scheduleJobIdPara);

    /**
     * 恢复任务
     *
     * @param scheduleJobIdPara the schedule job id
     * @return
     */
    public void resumeJob(String scheduleJobIdPara);

    /**
     * 获取任务对象
     * 
     * @param scheduleJobIdPara
     * @return
     */
    public StpScheduleJob get(String scheduleJobIdPara);

    /**
     * 查询任务列表
     * @return
     */
    public List<StpScheduleJob> queryList();

    /**
     * 获取运行中的任务列表
     *
     * @return
     */
    public List<StpScheduleJob> queryExecutingJobList();

    /**
     * 检查是否存在正在运行的任务
     *
     * @return
     */
    public boolean isExistsExecutingJob(StpScheduleJob scheduleJobPara);

    /**
     * 检查是否存在指定的任务
     *
     * @return
     */
    public boolean isExistsScheduleJob(StpScheduleJob scheduleJobPara);

    /**
     * 操作日志维护
     *
     * @return
     */
    public void insertLog(String operateTypePara, String jobDescPara, StpScheduleLog logPara, StpScheduleJob scheduleJobPara);

    /**
     * 操作日志查询
     *
     * @return
     */
    public List<StpScheduleLog> quaryJobLogList(StpScheduleJob scheduleJobPara);

    /**
     * 操作日志查询
     *
     * @return
     */
    public List<StpScheduleLog> quaryJobLogListForQry(StpScheduleLog stpScheduleLogPara);

    public void updateScheduleRelated(String descPara, StpScheduleJob scheduleJobPara);

    }
