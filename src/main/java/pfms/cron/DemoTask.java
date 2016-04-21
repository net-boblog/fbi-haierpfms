package pfms.cron;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import skyline.service.PlatformService;

import javax.annotation.Resource;

/**
 * Created by zhanrui on 2016/1/25.
 * Cron 任务示例
 * 注意异常信息需从JobExecutionException中抛出。
 */

@Component
public class DemoTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PlatformService platformService;

    public String runTask() throws JobExecutionException {
        try {
            long sn = platformService.obtainSeqNo("opLogSn");
            logger.info("opLogSn=" + sn);
            return "opLogSn=" + sn;
        } catch (Exception e) {
            //logger.error("获取序列号出错.", e);
            throw new JobExecutionException("获取序列号出错(opLogSn).", e);
        }
    }
}
