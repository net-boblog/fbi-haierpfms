package pfms.service;

import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.service.SbsTxnService;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ���⽻�׷���
 */
@Service
public class DataExchangeService {

    private static Logger logger = LoggerFactory.getLogger(DataExchangeService.class);
    @Autowired
    private SbsTxnService sbsTxnService;

    // SBS����ִ�е�
    public List<SOFForm> callSbsTxn(String txnCode, MTia tia, String operId) {
        String tellerid = operId;
        String termid = operId;
        return sbsTxnService.execute(termid, tellerid, txnCode, tia);
    }

    // SBS����ִ�е�,��������Ȩ
    public List<SOFForm> callSbsTxn(String auttlr, String autpwd, String txnCode, MTia tia, String operId) {
        String tellerid = operId;
        String termid = tellerid;
        return sbsTxnService.execute(termid, tellerid, auttlr, autpwd, txnCode, tia);
    }
}
