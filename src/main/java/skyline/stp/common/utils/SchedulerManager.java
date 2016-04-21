package skyline.stp.common.utils;

import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XIANGYANG on 2015-8-7.
 */

public class SchedulerManager implements ServletContextListener{
    private static final Logger logger = LoggerFactory.getLogger(SchedulerManager.class);

    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
                logger.info(">>>>>>>>>>>>schedule.shutdown()被调用");
            }
            logger.info(">>>>>>>>>>>>调度器关闭成功");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            logger.error(">>>>>>>>>>>>调度器关闭出错。",ex);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
            schedulerFactoryBean=context.getBean(SchedulerFactoryBean.class);
            Scheduler scheduler=schedulerFactoryBean.getScheduler();
            if (!scheduler.isStarted()){
                scheduler.start();
                logger.info(">>>>>>>>>>>>schedule.start()被调用");
            }
            logger.info(">>>>>>>>>>>>调度器启动成功。");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            logger.error(">>>>>>>>>>>>调度器启动失败。",ex);
        }
    }
}

