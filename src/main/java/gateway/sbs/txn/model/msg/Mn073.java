package gateway.sbs.txn.model.msg;

/**
 * n073-增值税入账数据查询
 */
public class Mn073 extends MTia {
    private String ORDDAT; // 日期
    private String ORDNUM; // 渠道号
    private String ANACDE; // 交易类别
    private String BEGNUM; // 起始笔数

    public String getORDDAT() {
        return ORDDAT;
    }

    public void setORDDAT(String ORDDAT) {
        this.ORDDAT = ORDDAT;
    }

    public String getORDNUM() {
        return ORDNUM;
    }

    public void setORDNUM(String ORDNUM) {
        this.ORDNUM = ORDNUM;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }

    public String getBEGNUM() {
        return BEGNUM;
    }

    public void setBEGNUM(String BEGNUM) {
        this.BEGNUM = BEGNUM;
    }
}
