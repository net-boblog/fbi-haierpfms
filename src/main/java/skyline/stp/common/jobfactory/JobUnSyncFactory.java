package skyline.stp.common.jobfactory;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.stp.common.utils.JobUtils;
import skyline.stp.repository.model.StpScheduleJob;

/**
 * Created by XIANGYANG on 2015-8-10.
 * 任务工厂类,同步
 */

public class JobUnSyncFactory implements Job {
    private static final Logger logger = LoggerFactory.getLogger(JobUnSyncFactory.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("JobSyncFactory execute");

        JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
        String scheduleJobPkid = (String) mergedJobDataMap.get(StpScheduleJob.JOB_PARAM_KEY);

        JobUtils.invokMethod(scheduleJobPkid);
    }
}
