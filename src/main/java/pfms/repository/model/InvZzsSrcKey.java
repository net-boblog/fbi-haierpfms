package pfms.repository.model;

public class InvZzsSrcKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column INV_ZZS_SRC.FBTIDX
     *
     * @mbggenerated Fri May 13 07:07:47 CST 2016
     */
    private String fbtidx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column INV_ZZS_SRC.DATASRC
     *
     * @mbggenerated Fri May 13 07:07:47 CST 2016
     */
    private String datasrc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column INV_ZZS_SRC.FBTIDX
     *
     * @return the value of INV_ZZS_SRC.FBTIDX
     *
     * @mbggenerated Fri May 13 07:07:47 CST 2016
     */
    public String getFbtidx() {
        return fbtidx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column INV_ZZS_SRC.FBTIDX
     *
     * @param fbtidx the value for INV_ZZS_SRC.FBTIDX
     *
     * @mbggenerated Fri May 13 07:07:47 CST 2016
     */
    public void setFbtidx(String fbtidx) {
        this.fbtidx = fbtidx == null ? null : fbtidx.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column INV_ZZS_SRC.DATASRC
     *
     * @return the value of INV_ZZS_SRC.DATASRC
     *
     * @mbggenerated Fri May 13 07:07:47 CST 2016
     */
    public String getDatasrc() {
        return datasrc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column INV_ZZS_SRC.DATASRC
     *
     * @param datasrc the value for INV_ZZS_SRC.DATASRC
     *
     * @mbggenerated Fri May 13 07:07:47 CST 2016
     */
    public void setDatasrc(String datasrc) {
        this.datasrc = datasrc == null ? null : datasrc.trim();
    }
}