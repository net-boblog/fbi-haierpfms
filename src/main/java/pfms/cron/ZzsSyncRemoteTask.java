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
 * ͬ��Զ�����ݵ�����
 */
@Component
public class ZzsSyncRemoteTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsHeadService invZzsHeadService;

    public void runTask() throws JobExecutionException {
        try {
            logger.info("----------[ͬ��Զ�����ݵ�����]��ʼ��" + ToolUtil.getDateTimeDashColon() + "----------");
            CustomInvZzsHead customInvZzsHead = new CustomInvZzsHead();
            // ��ѯ�������д���Ʊ������
            List<CustomInvZzsHead> unPrintList = invZzsHeadService.selectUnPrint(customInvZzsHead);
            List<String> unPrintXsddm = new ArrayList<String>();
            for (CustomInvZzsHead record : unPrintList) {
                unPrintXsddm.add(record.getXsddm());
            }
            // ��ѯ�������д����ϵ�����
            List<CustomInvZzsHead> unZuoFeiList = invZzsHeadService.selectUnZuoFei(customInvZzsHead);
            List<String> unZuoFeiFphm = new ArrayList<String>();
            for (CustomInvZzsHead record : unZuoFeiList) {
                unZuoFeiFphm.add(record.getFphm());
            }
            invZzsHeadService.syncRemote(unPrintXsddm, unZuoFeiFphm);
            logger.info("----------[ͬ��Զ�����ݵ�����]������" + ToolUtil.getDateTimeDashColon() + "----------");
        } catch (Exception e) {
            logger.error("ͬ��Զ�����ݵ�����ʧ�ܣ�", e);
            throw new JobExecutionException("ͬ��Զ�����ݵ�����ʧ�ܣ�" + e == null ? "" : e.getMessage(), e);
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
