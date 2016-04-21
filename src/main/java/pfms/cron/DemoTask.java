package pfms.cron;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import skyline.service.PlatformService;

import javax.annotation.Resource;

/**
 * Created by zhanrui on 2016/1/25.
 * Cron ����ʾ��
 * ע���쳣��Ϣ���JobExecutionException���׳���
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
            //logger.error("��ȡ���кų���.", e);
            throw new JobExecutionException("��ȡ���кų���(opLogSn).", e);
        }
    }
}
