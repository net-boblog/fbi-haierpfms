package skyline.repository.model;

public class Ptoperrole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTOPERROLE.ROLEID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTOPERROLE.OPERID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String operid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTOPERROLE.ROLEID
     *
     * @return the value of PTOPERROLE.ROLEID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTOPERROLE.ROLEID
     *
     * @param roleid the value for PTOPERROLE.ROLEID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTOPERROLE.OPERID
     *
     * @return the value of PTOPERROLE.OPERID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getOperid() {
        return operid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTOPERROLE.OPERID
     *
     * @param operid the value for PTOPERROLE.OPERID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setOperid(String operid) {
        this.operid = operid == null ? null : operid.trim();
    }
}