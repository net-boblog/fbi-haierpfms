package skyline.repository.model;

public class Ptmenufavo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENUFAVO.OPERID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String operid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENUFAVO.MENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String menuid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENUFAVO.OPERID
     *
     * @return the value of PTMENUFAVO.OPERID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getOperid() {
        return operid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENUFAVO.OPERID
     *
     * @param operid the value for PTMENUFAVO.OPERID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setOperid(String operid) {
        this.operid = operid == null ? null : operid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENUFAVO.MENUID
     *
     * @return the value of PTMENUFAVO.MENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getMenuid() {
        return menuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENUFAVO.MENUID
     *
     * @param menuid the value for PTMENUFAVO.MENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }
}