package gateway.sbs.txn.action;

import gateway.sbs.core.domain.SOFForm;
import gateway.sbs.txn.model.msg.MTia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractTxnAction {

    private static Logger logger = LoggerFactory.getLogger(AbstractTxnAction.class);

    public List<SOFForm> run(String termid, String tellerid, MTia tia) {
        try {
            return process(termid, tellerid, null, null, tia);
        } catch (Exception e) {
            logger.error("�����쳣", e);
            throw new RuntimeException(e.getMessage() == null ? "�����쳣." : e.getMessage());
        }
    }

    public List<SOFForm> run(String termid, String tellerid, String auttlr, String autpwd, MTia tia) {
        try {
            return process(termid, tellerid, auttlr, autpwd, tia);
        } catch (Exception e) {
            logger.error("�����쳣", e);
            throw new RuntimeException(e.getMessage() == null ? "�����쳣." : e.getMessage());
        }
    }

    abstract protected List<SOFForm> process(String termid, String tellerid, String auttlr, String autpwd, MTia tia) throws Exception;
}
