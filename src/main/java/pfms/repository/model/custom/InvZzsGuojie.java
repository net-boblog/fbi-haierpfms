package pfms.repository.model.custom;

/**
 * ������ֵ˰����
 */
public class InvZzsGuojie {
    private String RESULT;     // ���
    private String TOTALCOUNT; // �ܼ�¼��
    private String DETAIL;     // ��ϸ��Ϣ

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

    public static class Bean {
        private String IBMSNO;         // ��ˮ��
        private String CUS_NO;         // ����ͻ���
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
    }
}
