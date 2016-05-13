package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ֵ˰�������ݲ�ѯ
 */
public class T467 extends SOFFormBody {

    private String totcnt = "0";            // �ܼ�¼��
    private String curcnt = "0";            // �����ڼ�¼��

    private List<Bean> beanList = new ArrayList<Bean>();

    @Override
    public void assembleFields(int offset, byte[] buffer) {
        byte[] totcntBytes = new byte[6];
        byte[] curcntBytes = new byte[6];
        System.arraycopy(buffer, offset, totcntBytes, 0, 6);
        totcnt = new String(totcntBytes);
        System.arraycopy(buffer, offset + 6, curcntBytes, 0, 6);
        curcnt = new String(curcntBytes);

        int index = offset + 12;
        int beanLength = 330;
        do {
            Bean bean = new Bean();
            bean.assembleFields(index, buffer);
            beanList.add(bean);
            index += beanLength;
        } while (index < buffer.length);
    }

    public List<Bean> getBeanList() {
        return beanList;
    }

    public String getTotcnt() {
        return totcnt;
    }

    public String getCurcnt() {
        return curcnt;
    }

    public void setTotcnt(String totcnt) {
        this.totcnt = totcnt;
    }

    public void setCurcnt(String curcnt) {
        this.curcnt = curcnt;
    }

    public class Bean extends AssembleModel {
        {
            fieldTypes = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            fieldLengths = new int[]{18, 7, 3, 4, 40, 4, 40, 6, 17, 17, 17, 17, 17, 9, 17, 17, 80};
        }

        private String FBTIDX; // ��ˮ��
        private String CUSIDT; // ����ͻ���
        private String CURCDE; // ����ұ�
        private String PRDCDE; // ��Ʒ����
        private String PRDNAM; // ��Ʒ����
        private String PRDTYP; // �ͺ�
        private String PRDUNT; // ��Ʒ��λ
        private String PRDCNT; // ����
        private String TOTPRI; // ��˰����
        private String TOTAMT; // ��˰���
        private String CLNPRI; // ����˰����
        private String CLNAMT; // ����˰���
        private String TAXAMT; // ˰��
        private String TAXRAT; // ˰��
        private String OFCAMT; // �ۿ۲���˰���
        private String OFCTAX; // �ۿ�˰��
        private String RETAUX; // ��ע

        public String getFBTIDX() {
            return FBTIDX;
        }

        public void setFBTIDX(String FBTIDX) {
            this.FBTIDX = FBTIDX;
        }

        public String getCUSIDT() {
            return CUSIDT;
        }

        public void setCUSIDT(String CUSIDT) {
            this.CUSIDT = CUSIDT;
        }

        public String getCURCDE() {
            return CURCDE;
        }

        public void setCURCDE(String CURCDE) {
            this.CURCDE = CURCDE;
        }

        public String getPRDCDE() {
            return PRDCDE;
        }

        public void setPRDCDE(String PRDCDE) {
            this.PRDCDE = PRDCDE;
        }

        public String getPRDNAM() {
            return PRDNAM;
        }

        public void setPRDNAM(String PRDNAM) {
            this.PRDNAM = PRDNAM;
        }

        public String getPRDTYP() {
            return PRDTYP;
        }

        public void setPRDTYP(String PRDTYP) {
            this.PRDTYP = PRDTYP;
        }

        public String getPRDUNT() {
            return PRDUNT;
        }

        public void setPRDUNT(String PRDUNT) {
            this.PRDUNT = PRDUNT;
        }

        public String getPRDCNT() {
            return PRDCNT;
        }

        public void setPRDCNT(String PRDCNT) {
            this.PRDCNT = PRDCNT;
        }

        public String getTOTPRI() {
            return TOTPRI;
        }

        public void setTOTPRI(String TOTPRI) {
            this.TOTPRI = TOTPRI;
        }

        public String getTOTAMT() {
            return TOTAMT;
        }

        public void setTOTAMT(String TOTAMT) {
            this.TOTAMT = TOTAMT;
        }

        public String getCLNPRI() {
            return CLNPRI;
        }

        public void setCLNPRI(String CLNPRI) {
            this.CLNPRI = CLNPRI;
        }

        public String getCLNAMT() {
            return CLNAMT;
        }

        public void setCLNAMT(String CLNAMT) {
            this.CLNAMT = CLNAMT;
        }

        public String getTAXAMT() {
            return TAXAMT;
        }

        public void setTAXAMT(String TAXAMT) {
            this.TAXAMT = TAXAMT;
        }

        public String getTAXRAT() {
            return TAXRAT;
        }

        public void setTAXRAT(String TAXRAT) {
            this.TAXRAT = TAXRAT;
        }

        public String getOFCAMT() {
            return OFCAMT;
        }

        public void setOFCAMT(String OFCAMT) {
            this.OFCAMT = OFCAMT;
        }

        public String getOFCTAX() {
            return OFCTAX;
        }

        public void setOFCTAX(String OFCTAX) {
            this.OFCTAX = OFCTAX;
        }

        public String getRETAUX() {
            return RETAUX;
        }

        public void setRETAUX(String RETAUX) {
            this.RETAUX = RETAUX;
        }
    }
}
