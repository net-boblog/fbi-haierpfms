package pfms.repository.model.custom;

import pfms.repository.model.InvZzsSrc;

/**
 * InvZzsSrc与InvZzsCust的关联
 */
public class CustomInvZzsSrc extends InvZzsSrc {
    private String txnDateStart;
    private String txnDateEnd;
    private String khswdjh;
    private String khmc;
    private String khsj;
    private String khdz;
    private String khyh;
    private String yhzh;

    public String getTxnDateStart() {
        return txnDateStart;
    }

    public void setTxnDateStart(String txnDateStart) {
        this.txnDateStart = txnDateStart;
    }

    public String getTxnDateEnd() {
        return txnDateEnd;
    }

    public void setTxnDateEnd(String txnDateEnd) {
        this.txnDateEnd = txnDateEnd;
    }

    public String getKhswdjh() {
        return khswdjh;
    }

    public void setKhswdjh(String khswdjh) {
        this.khswdjh = khswdjh;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getKhsj() {
        return khsj;
    }

    public void setKhsj(String khsj) {
        this.khsj = khsj;
    }

    public String getKhdz() {
        return khdz;
    }

    public void setKhdz(String khdz) {
        this.khdz = khdz;
    }

    public String getKhyh() {
        return khyh;
    }

    public void setKhyh(String khyh) {
        this.khyh = khyh;
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }
}
