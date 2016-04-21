package skyline.stp.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import skyline.stp.repository.model.StpScheduleJob;
import skyline.stp.repository.model.StpScheduleLog;
import skyline.stp.service.ScheduleJobService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/**
 * Created by XIANGYANG on 2015-8-10.
 */

public class JobUtils {
    public final static Logger logger = Logger.getLogger(JobUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJobPkidPara
     */
    public static void invokMethod(String scheduleJobPkidPara) {
        ScheduleJobService scheduleJobService = SpringUtils.getBean("scheduleJobServiceImpl");
        StpScheduleJob scheduleJob = scheduleJobService.get(scheduleJobPkidPara);

        Object object = null;
        Class clazz = null;
        String strDesc = null;
        String strPreInfoDetail = "[" + scheduleJob.getJobName() + "]" + "[" + scheduleJob.getJobGroup() + "]" + "[" + scheduleJob.getCronExpression() + "]";

        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
            try {
                object = SpringUtils.getBean(scheduleJob.getSpringId());
            } catch (Exception e) {
                logger.error(strPreInfoDetail + "根据springId获取实例出错。", e);
                if (StringUtils.isNotBlank(scheduleJob.getJobAction())) {
                    try {
                        clazz = Class.forName(scheduleJob.getJobAction());
                        object = clazz.newInstance();
                    } catch (Exception e1) {
                        logger.error(strPreInfoDetail + "根据动作类初始化实例出错。", e1);
                    }
                }
            }
        } else if (StringUtils.isNotBlank(scheduleJob.getJobAction())) {
            try {
                clazz = Class.forName(scheduleJob.getJobAction());
                object = clazz.newInstance();
            } catch (Exception e) {
                logger.error(strPreInfoDetail + "根据动作类初始化实例出错。", e);
            }
        }

        if (object == null) {
            strDesc = strPreInfoDetail + "任务未启动成功，请检查SpringId以及动作类是否配置正确。";
            logger.error(strDesc);
            scheduleJob.setFailNum(scheduleJob.getFailNum() + 1);
            scheduleJobService.updateScheduleRelated(strDesc, scheduleJob);
        } else {
            clazz = object.getClass();
            Method method = null;
            try {
                method = clazz.getDeclaredMethod(scheduleJob.getJobMethod());
            } catch (Exception e) {
                strDesc = strPreInfoDetail + "任务未启动成功，动作函数设置错误。";
                logger.error(strDesc, e);
                scheduleJob.setFailNum(scheduleJob.getFailNum() + 1);
                scheduleJobService.updateScheduleRelated(strDesc, scheduleJob);
                return;
            }

            strDesc = strPreInfoDetail + "启动成功。";
            logger.info(strDesc);
            scheduleJobService.insertLog(scheduleJob.getScheduleType(), strDesc, new StpScheduleLog(), scheduleJob);

            String jobRtnMsg = "";
            long start = System.currentTimeMillis();
            try {
                Object rtnObj = method.invoke(object);
                Type returnType = method.getReturnType();// 返回类型
                if (String.class.equals(returnType)) {
                    if (rtnObj != null)
                        jobRtnMsg = (String) rtnObj;
                }
            } catch (Exception e) {
                strDesc = strPreInfoDetail + "任务执行过程中出错。";

                Throwable t = e.getCause();
                String em = t.getMessage();
                if (em != null) {
                    if (em.getBytes().length > 300) {
                        em = em.substring(0, 300);
                    }
                    strDesc += em;
                }
                logger.error(strDesc, e);
                scheduleJob.setFailNum(scheduleJob.getFailNum() + 1);
                scheduleJobService.updateScheduleRelated(strDesc, scheduleJob);
                return;
            }

            strDesc = strPreInfoDetail + "任务完成, 耗时:" + (double) (System.currentTimeMillis() - start) / 1000 + "s. " + jobRtnMsg;
            if (strDesc.getBytes().length > 500) {
                strDesc = strDesc.substring(0, 500);
            }
            scheduleJob.setSucessNum(scheduleJob.getSucessNum() + 1);
            scheduleJobService.updateScheduleRelated(strDesc, scheduleJob);
        }
    }
}
