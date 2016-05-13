package gateway.sbs.txn.model.form.re;

import gateway.sbs.core.domain.AssembleModel;
import gateway.sbs.core.domain.SOFFormBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 增值税入账数据查询
 */
public class T467 extends SOFFormBody {

    private String totcnt = "0";            // 总记录数
    private String curcnt = "0";            // 本包内记录数

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

        private String FBTIDX; // 流水号
        private String CUSIDT; // 付款客户号
        private String CURCDE; // 付款币别
        private String PRDCDE; // 产品代码
        private String PRDNAM; // 产品名称
        private String PRDTYP; // 型号
        private String PRDUNT; // 产品单位
        private String PRDCNT; // 数量
        private String TOTPRI; // 含税单价
        private String TOTAMT; // 含税金额
        private String CLNPRI; // 不含税单价
        private String CLNAMT; // 不含税金额
        private String TAXAMT; // 税额
        private String TAXRAT; // 税率
        private String OFCAMT; // 折扣不含税金额
        private String OFCTAX; // 折扣税额
        private String RETAUX; // 备注

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
