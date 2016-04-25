package pfms.cron;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pfms.repository.model.custom.CustomInvZzsHead;
import pfms.service.inv.InvZzsHeadService;
import pfms.util.ToolUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 同步远程数据到本地
 */
@Component
public class ZzsSyncRemoteTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsHeadService invZzsHeadService;

    public void runTask() throws JobExecutionException {
        try {
            logger.info("----------[同步远程数据到本地]开始：" + ToolUtil.getDateTimeDashColon() + "----------");
            CustomInvZzsHead customInvZzsHead = new CustomInvZzsHead();
            // 查询本地所有待开票的数据
            List<CustomInvZzsHead> unPrintList = invZzsHeadService.selectUnPrint(customInvZzsHead);
            List<String> unPrintXsddm = new ArrayList<String>();
            for (CustomInvZzsHead record : unPrintList) {
                unPrintXsddm.add(record.getXsddm());
            }
            // 查询本地所有待作废的数据
            List<CustomInvZzsHead> unZuoFeiList = invZzsHeadService.selectUnZuoFei(customInvZzsHead);
            List<String> unZuoFeiFphm = new ArrayList<String>();
            for (CustomInvZzsHead record : unZuoFeiList) {
                unZuoFeiFphm.add(record.getFphm());
            }
            invZzsHeadService.syncRemote(unPrintXsddm, unZuoFeiFphm);
            logger.info("----------[同步远程数据到本地]结束：" + ToolUtil.getDateTimeDashColon() + "----------");
        } catch (Exception e) {
            logger.error("同步远程数据到本地失败！", e);
            throw new JobExecutionException("同步远程数据到本地失败！" + e == null ? "" : e.getMessage(), e);
        }
    }

    //==============================================get set=====================================================
    public InvZzsHeadService getInvZzsHeadService() {
        return invZzsHeadService;
    }

    public void setInvZzsHeadService(InvZzsHeadService invZzsHeadService) {
        this.invZzsHeadService = invZzsHeadService;
    }
}
