package skyline.stp.view.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import skyline.stp.service.ScheduleJobService;

/**
 * Created by XIANGYANG on 2015-8-7.
 * 定时任务初始化
 */

@Component
public class ScheduleJobInit {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobInit.class);

    /** 定时任务service */
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void init() {
        try {
            if (logger.isInfoEnabled()) {
                logger.info(">>>>>>>>>>>>定时任务初始化开始...");
            }
            scheduleJobService.initScheduleJob();
            if (logger.isInfoEnabled()) {
                logger.info(">>>>>>>>>>>>定时任务初始化完成！");
            }
        }catch (Exception e){
            if (logger.isInfoEnabled()) {
                logger.info(">>>>>>>>>>>>定时任务初始化出错！");
            }
        }
    }

}
