package skyline.stp.view.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import skyline.stp.service.ScheduleJobService;

/**
 * Created by XIANGYANG on 2015-8-7.
 * ��ʱ�����ʼ��
 */

@Component
public class ScheduleJobInit {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobInit.class);

    /** ��ʱ����service */
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * ��Ŀ����ʱ��ʼ��
     */
    @PostConstruct
    public void init() {
        try {
            if (logger.isInfoEnabled()) {
                logger.info(">>>>>>>>>>>>��ʱ�����ʼ����ʼ...");
            }
            scheduleJobService.initScheduleJob();
            if (logger.isInfoEnabled()) {
                logger.info(">>>>>>>>>>>>��ʱ�����ʼ����ɣ�");
            }
        }catch (Exception e){
            if (logger.isInfoEnabled()) {
                logger.info(">>>>>>>>>>>>��ʱ�����ʼ������");
            }
        }
    }

}
