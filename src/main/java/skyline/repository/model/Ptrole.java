package skyline.repository.model;

public class Ptrole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTROLE.ROLEID
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    private String roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTROLE.ROLEDESC
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    private String roledesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTROLE.STATUS
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTROLE.ROLEID
     *
     * @return the value of PTROLE.ROLEID
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTROLE.ROLEID
     *
     * @param roleid the value for PTROLE.ROLEID
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTROLE.ROLEDESC
     *
     * @return the value of PTROLE.ROLEDESC
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    public String getRoledesc() {
        return roledesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTROLE.ROLEDESC
     *
     * @param roledesc the value for PTROLE.ROLEDESC
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTROLE.STATUS
     *
     * @return the value of PTROLE.STATUS
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTROLE.STATUS
     *
     * @param status the value for PTROLE.STATUS
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}