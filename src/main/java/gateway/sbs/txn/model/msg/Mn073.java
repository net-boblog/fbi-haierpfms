package gateway.sbs.txn.model.msg;

/**
 * n073-��ֵ˰�������ݲ�ѯ
 */
public class Mn073 extends MTia {
    private String ORDDAT; // ����
    private String ORDNUM; // ������
    private String ANACDE; // �������
    private String BEGNUM; // ��ʼ����

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
