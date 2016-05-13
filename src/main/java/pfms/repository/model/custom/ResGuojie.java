package pfms.repository.model.custom;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * ������Ӧ����
 */
@XStreamAlias("pifcs")
public class ResGuojie {
    @XStreamAlias("Head")
    private ResHeader resHeader;

    @XStreamAlias("Body")
    private ResBody resBody;

    public ResHeader getResHeader() {
        return resHeader;
    }

    public void setResHeader(ResHeader resHeader) {
        this.resHeader = resHeader;
    }

    public ResBody getResBody() {
        return resBody;
    }

    public void setResBody(ResBody resBody) {
        this.resBody = resBody;
    }

    public static class ResHeader {
        private String branch_no;    // ������
        private String ret_code;     // ��Ӧ��
        private String tx_code;      // ������
        private String teller_no;    // ��Ա��
        private String ret_explain;  // ��Ӧ��˵��
        private String channel_flag; // ������ʶ

        public String getBranch_no() {
            return branch_no;
        }

        public void setBranch_no(String branch_no) {
            this.branch_no = branch_no;
        }

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getTx_code() {
            return tx_code;
        }

        public void setTx_code(String tx_code) {
            this.tx_code = tx_code;
        }

        public String getTeller_no() {
            return teller_no;
        }

        public void setTeller_no(String teller_no) {
            this.teller_no = teller_no;
        }

        public String getRet_explain() {
            return ret_explain;
        }

        public void setRet_explain(String ret_explain) {
            this.ret_explain = ret_explain;
        }

        public String getChannel_flag() {
            return channel_flag;
        }

        public void setChannel_flag(String channel_flag) {
            this.channel_flag = channel_flag;
        }
    }

    public static class ResBody {
        private String TOTALCOUNT; // �ܼ�¼��
        private String RESULT;     // ���
        private String DETAIL;     // ��ϸ��Ϣ

        @XStreamAlias("list")
        private ResContent resContent;

        public String getRESULT() {
            return RESULT;
        }

        public void setRESULT(String RESULT) {
            this.RESULT = RESULT;
        }

        public String getTOTALCOUNT() {
            return TOTALCOUNT;
        }

        public void setTOTALCOUNT(String TOTALCOUNT) {
            this.TOTALCOUNT = TOTALCOUNT;
        }

        public String getDETAIL() {
            return DETAIL;
        }

        public void setDETAIL(String DETAIL) {
            this.DETAIL = DETAIL;
        }

        public ResContent getResContent() {
            return resContent;
        }

        public void setResContent(ResContent resContent) {
            this.resContent = resContent;
        }
    }

    public static class ResContent {
        @XStreamImplicit(itemFieldName="row")
        private List<Bean> beanList; // ѭ����¼

        public List<Bean> getBeanList() {
            return beanList;
        }

        public void setBeanList(List<Bean> beanList) {
            this.beanList = beanList;
        }
    }

    public static class Bean {
        private String IBMSNO;         // ��ˮ��
        private String CUS_NO;         // ����ͻ���
        private String PAYER_NAME;     // ����������
        private String CURRENCYTYPE;   // ����ұ�
        private String UPC_CODE;       // ��Ʒ����
        private String UPC_NAME;       // ��Ʒ����
        private String MODEL;          // �ͺ�
        private String PRODUCT_UNIT;   // ��Ʒ��λ
        private String QTY;            // ����
        private String UNIT_PRICE;     // ��˰����
        private String AMOUNT;         // ��˰���
        private String NOUNIT_PRICE;   // ����˰����
        private String NOTAX_AMOUNT;   // ����˰���
        private String TAX_AMOUNT;     // ˰��
        private String TAX_RATE;       // ˰��
        private String DISCOUNT_NOTAX; // �ۿ۲���˰���
        private String DISCOUNT_TAX;   // �ۿ�˰��
        private String REMARKS;        // ��ע

        public String getIBMSNO() {
            return IBMSNO;
        }

        public void setIBMSNO(String IBMSNO) {
            this.IBMSNO = IBMSNO;
        }

        public String getCUS_NO() {
            return CUS_NO;
        }

        public void setCUS_NO(String CUS_NO) {
            this.CUS_NO = CUS_NO;
        }

        public String getPAYER_NAME() {
            return PAYER_NAME;
        }

        public void setPAYER_NAME(String PAYER_NAME) {
            this.PAYER_NAME = PAYER_NAME;
        }

        public String getCURRENCYTYPE() {
            return CURRENCYTYPE;
        }

        public void setCURRENCYTYPE(String CURRENCYTYPE) {
            this.CURRENCYTYPE = CURRENCYTYPE;
        }

        public String getUPC_CODE() {
            return UPC_CODE;
        }

        public void setUPC_CODE(String UPC_CODE) {
            this.UPC_CODE = UPC_CODE;
        }

        public String getUPC_NAME() {
            return UPC_NAME;
        }

        public void setUPC_NAME(String UPC_NAME) {
            this.UPC_NAME = UPC_NAME;
        }

        public String getMODEL() {
            return MODEL;
        }

        public void setMODEL(String MODEL) {
            this.MODEL = MODEL;
        }

        public String getPRODUCT_UNIT() {
            return PRODUCT_UNIT;
        }

        public void setPRODUCT_UNIT(String PRODUCT_UNIT) {
            this.PRODUCT_UNIT = PRODUCT_UNIT;
        }

        public String getQTY() {
            return QTY;
        }

        public void setQTY(String QTY) {
            this.QTY = QTY;
        }

        public String getUNIT_PRICE() {
            return UNIT_PRICE;
        }

        public void setUNIT_PRICE(String UNIT_PRICE) {
            this.UNIT_PRICE = UNIT_PRICE;
        }

        public String getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(String AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getNOUNIT_PRICE() {
            return NOUNIT_PRICE;
        }

        public void setNOUNIT_PRICE(String NOUNIT_PRICE) {
            this.NOUNIT_PRICE = NOUNIT_PRICE;
        }

        public String getNOTAX_AMOUNT() {
            return NOTAX_AMOUNT;
        }

        public void setNOTAX_AMOUNT(String NOTAX_AMOUNT) {
            this.NOTAX_AMOUNT = NOTAX_AMOUNT;
        }

        public String getTAX_AMOUNT() {
            return TAX_AMOUNT;
        }

        public void setTAX_AMOUNT(String TAX_AMOUNT) {
            this.TAX_AMOUNT = TAX_AMOUNT;
        }

        public String getTAX_RATE() {
            return TAX_RATE;
        }

        public void setTAX_RATE(String TAX_RATE) {
            this.TAX_RATE = TAX_RATE;
        }

        public String getDISCOUNT_NOTAX() {
            return DISCOUNT_NOTAX;
        }

        public void setDISCOUNT_NOTAX(String DISCOUNT_NOTAX) {
            this.DISCOUNT_NOTAX = DISCOUNT_NOTAX;
        }

        public String getDISCOUNT_TAX() {
            return DISCOUNT_TAX;
        }

        public void setDISCOUNT_TAX(String DISCOUNT_TAX) {
            this.DISCOUNT_TAX = DISCOUNT_TAX;
        }

        public String getREMARKS() {
            return REMARKS;
        }

        public void setREMARKS(String REMARKS) {
            this.REMARKS = REMARKS;
        }
    }
}
