package pfms.repository.model.custom;

import pfms.repository.model.InvZzsSrc;

/**
 * InvZzsSrc与InvZzsCust的关联
 */
public class CustomInvZzsSrc extends InvZzsSrc {
    private String khswdjh;
    private String khmc;
    private String khsj;
    private String khdz;
    private String khyh;

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
}
