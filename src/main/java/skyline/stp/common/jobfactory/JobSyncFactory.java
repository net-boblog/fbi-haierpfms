package skyline.stp.common.jobfactory;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.stp.common.utils.JobUtils;
import skyline.stp.repository.model.StpScheduleJob;

import java.text.SimpleDateFormat;

/**
 * Created by XIANGYANG on 2015-8-10.
 * 任务工厂类,非同步
 */

@DisallowConcurrentExecution
public class JobSyncFactory implements Job {
    private static final Logger logger = LoggerFactory.getLogger(JobSyncFactory.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.info("JobFactory execute");
            
            JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
            String scheduleJobPkid = (String) mergedJobDataMap.get(StpScheduleJob.JOB_PARAM_KEY);
//            String scheduleJobPkid = (String)context.getTrigger().getJobDataMap().get(StpScheduleJob.JOB_PARAM_KEY);

            JobUtils.invokMethod(scheduleJobPkid);
        }catch (Exception e){
            //发短信通知
            logger.error("任务出错",e);
        }
    }
}
