package gateway.sbs.service;

import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.action.AbstractTxnAction;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * SBS���׷����
 */
@Service
public class SbsTxnService {
    private static Logger logger = LoggerFactory.getLogger(SbsTxnService.class);

    public List<SOFForm> execute(String termid, String tellid, String auttlr, String autpwd, String txnCode, MTia tia) {
        WebApplicationContext springContext = ContextLoader.getCurrentWebApplicationContext();
        AbstractTxnAction txnAction = (AbstractTxnAction) springContext.getBean("txn" + txnCode + "Action");
        return txnAction.run(termid, tellid, auttlr, autpwd, tia);
    }

    public List<SOFForm> execute(String termid, String tellid, String txnCode, MTia tia) {
        WebApplicationContext springContext = ContextLoader.getCurrentWebApplicationContext();
        AbstractTxnAction txnAction = (AbstractTxnAction) springContext.getBean("txn" + txnCode + "Action");
        return txnAction.run(termid, tellid, tia);
    }
}
