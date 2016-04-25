package pfms.repository.model.custom;

/**
 * 国结增值税数据
 */
public class InvZzsGuojie {
    private String RESULT;     // 结果
    private String TOTALCOUNT; // 总记录数
    private String DETAIL;     // 详细信息

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
        private String IBMSNO;         // 流水号
        private String CUS_NO;         // 付款客户号
        private String CURRENCYTYPE;   // 付款币别
        private String UPC_CODE;       // 产品代码
        private String UPC_NAME;       // 产品名称
        private String MODEL;          // 型号
        private String PRODUCT_UNIT;   // 产品单位
        private String QTY;            // 数量
        private String UNIT_PRICE;     // 含税单价
        private String AMOUNT;         // 含税金额
        private String NOUNIT_PRICE;   // 不含税单价
        private String NOTAX_AMOUNT;   // 不含税金额
        private String TAX_AMOUNT;     // 税额
        private String TAX_RATE;       // 税率
        private String DISCOUNT_NOTAX; // 折扣不含税金额
        private String DISCOUNT_TAX;   // 折扣税额

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
