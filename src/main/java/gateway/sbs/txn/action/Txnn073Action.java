package gateway.sbs.txn.action;

import gateway.sbs.core.SBSResponse;
import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.CoreTxnService;
import gateway.sbs.txn.model.msg.MTia;
import gateway.sbs.txn.model.msg.Mn073;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * n073-��ֵ˰�������ݲ�ѯ
 */
@Component
public class Txnn073Action extends AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(Txnn073Action.class);
    @Autowired
    private CoreTxnService coreTxnService;

    @Override
    public List<SOFForm> process(String termid, String tellid, String auttlr, String autpwd, MTia tia) throws Exception {

        Mn073 mn073 = (Mn073) tia;
        logger.info("[n073-��ֵ˰�������ݲ�ѯ] ���ڣ�" + mn073.getORDDAT() +
                " �����ţ�" + mn073.getORDNUM() +
                " �������" + mn073.getANACDE() +
                " ��ʼ������" + mn073.getBEGNUM());

        List<String> paramList = new ArrayList<String>();
        paramList.add(mn073.getORDDAT());
        paramList.add(mn073.getORDNUM());
        paramList.add(mn073.getANACDE());
        paramList.add(mn073.getBEGNUM());

        // ִ��sbs����
        SBSResponse response = coreTxnService.execute(termid, tellid, "n073", paramList);

        StringBuffer rtnFormCodes = new StringBuffer("[n073-��ֵ˰�������ݲ�ѯ] ���ڣ�" + mn073.getORDDAT() +
                " �����ţ�" + mn073.getORDNUM() +
                " �������" + mn073.getANACDE() +
                " ��ʼ������" + mn073.getBEGNUM() + " �����룺");
        for (String formcode : response.getFormCodes()) {
            rtnFormCodes.append("[").append(formcode).append("]");
        }
        logger.info(rtnFormCodes.toString());
        return response.getSofForms();
    }
}
