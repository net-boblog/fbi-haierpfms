package pfms.cron;

import org.joda.time.DateTime;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pfms.service.inv.InvZzsSrcService;
import pfms.util.HttpClientUtil;

import javax.annotation.Resource;

/**
 * �ӹ���ȡ����ֵ˰���ݲ�����ԭʼ���ݱ�
 */
@Component
public class ZzsGuojieTask {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private InvZzsSrcService invZzsSrcService;

    public void runTask() throws JobExecutionException {
        try {
            String txnDate = new DateTime().plusDays(-1).toString("yyyy-MM-dd");
            txnDate = "2016-04-20";
            String responseXml = HttpClientUtil.exchange(txnDate.replace("-", ""));
//            if (dataList != null && dataList.size() > 0) {
//                invZzsSrcService.insert(txnDate, dataList);
//            }
        } catch (Exception e) {
            logger.error("�ӹ���ȡ����ֵ˰����ʧ�ܣ�", e);
            throw new JobExecutionException("�ӹ���ȡ����ֵ˰����ʧ�ܣ�" + e == null ? "" : e.getMessage(), e);
        }
    }

    //==============================================get set=====================================================
    public InvZzsSrcService getInvZzsSrcService() {
        return invZzsSrcService;
    }

    public void setInvZzsSrcService(InvZzsSrcService invZzsSrcService) {
        this.invZzsSrcService = invZzsSrcService;
    }
}
