package skyline.repository.model;

import java.math.BigDecimal;
import java.util.Date;

public class Ptmenu {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.MENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String menuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.PARENTMENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String parentmenuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.MENULEVEL
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private Short menulevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.ISLEAF
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private Short isleaf;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.MENUDESC
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String menudesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.MENULABEL
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String menulabel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.MENUICON
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String menuicon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.OPENICON
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String openicon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.TARGETMACHINE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String targetmachine;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.MENUACTION
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String menuaction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.OPENWINDOW
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String openwindow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.WINDOWWIDTH
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private Integer windowwidth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.WINDOWHEIGHT
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private Integer windowheight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.LEVELIDX
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private Integer levelidx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.FILLSTR50
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String fillstr50;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.FILLSTR100
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private String fillstr100;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.FILLINT1
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private Long fillint1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.FILLDBL2
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private BigDecimal filldbl2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PTMENU.FILLDATE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    private Date filldate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.MENUID
     *
     * @return the value of PTMENU.MENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getMenuid() {
        return menuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.MENUID
     *
     * @param menuid the value for PTMENU.MENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.PARENTMENUID
     *
     * @return the value of PTMENU.PARENTMENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getParentmenuid() {
        return parentmenuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.PARENTMENUID
     *
     * @param parentmenuid the value for PTMENU.PARENTMENUID
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setParentmenuid(String parentmenuid) {
        this.parentmenuid = parentmenuid == null ? null : parentmenuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.MENULEVEL
     *
     * @return the value of PTMENU.MENULEVEL
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Short getMenulevel() {
        return menulevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.MENULEVEL
     *
     * @param menulevel the value for PTMENU.MENULEVEL
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setMenulevel(Short menulevel) {
        this.menulevel = menulevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.ISLEAF
     *
     * @return the value of PTMENU.ISLEAF
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Short getIsleaf() {
        return isleaf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.ISLEAF
     *
     * @param isleaf the value for PTMENU.ISLEAF
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setIsleaf(Short isleaf) {
        this.isleaf = isleaf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.MENUDESC
     *
     * @return the value of PTMENU.MENUDESC
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getMenudesc() {
        return menudesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.MENUDESC
     *
     * @param menudesc the value for PTMENU.MENUDESC
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setMenudesc(String menudesc) {
        this.menudesc = menudesc == null ? null : menudesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.MENULABEL
     *
     * @return the value of PTMENU.MENULABEL
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getMenulabel() {
        return menulabel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.MENULABEL
     *
     * @param menulabel the value for PTMENU.MENULABEL
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setMenulabel(String menulabel) {
        this.menulabel = menulabel == null ? null : menulabel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.MENUICON
     *
     * @return the value of PTMENU.MENUICON
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getMenuicon() {
        return menuicon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.MENUICON
     *
     * @param menuicon the value for PTMENU.MENUICON
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon == null ? null : menuicon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.OPENICON
     *
     * @return the value of PTMENU.OPENICON
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getOpenicon() {
        return openicon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.OPENICON
     *
     * @param openicon the value for PTMENU.OPENICON
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setOpenicon(String openicon) {
        this.openicon = openicon == null ? null : openicon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.TARGETMACHINE
     *
     * @return the value of PTMENU.TARGETMACHINE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getTargetmachine() {
        return targetmachine;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.TARGETMACHINE
     *
     * @param targetmachine the value for PTMENU.TARGETMACHINE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setTargetmachine(String targetmachine) {
        this.targetmachine = targetmachine == null ? null : targetmachine.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.MENUACTION
     *
     * @return the value of PTMENU.MENUACTION
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getMenuaction() {
        return menuaction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.MENUACTION
     *
     * @param menuaction the value for PTMENU.MENUACTION
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setMenuaction(String menuaction) {
        this.menuaction = menuaction == null ? null : menuaction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.OPENWINDOW
     *
     * @return the value of PTMENU.OPENWINDOW
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getOpenwindow() {
        return openwindow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.OPENWINDOW
     *
     * @param openwindow the value for PTMENU.OPENWINDOW
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setOpenwindow(String openwindow) {
        this.openwindow = openwindow == null ? null : openwindow.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.WINDOWWIDTH
     *
     * @return the value of PTMENU.WINDOWWIDTH
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Integer getWindowwidth() {
        return windowwidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.WINDOWWIDTH
     *
     * @param windowwidth the value for PTMENU.WINDOWWIDTH
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setWindowwidth(Integer windowwidth) {
        this.windowwidth = windowwidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.WINDOWHEIGHT
     *
     * @return the value of PTMENU.WINDOWHEIGHT
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Integer getWindowheight() {
        return windowheight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.WINDOWHEIGHT
     *
     * @param windowheight the value for PTMENU.WINDOWHEIGHT
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setWindowheight(Integer windowheight) {
        this.windowheight = windowheight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.LEVELIDX
     *
     * @return the value of PTMENU.LEVELIDX
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Integer getLevelidx() {
        return levelidx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.LEVELIDX
     *
     * @param levelidx the value for PTMENU.LEVELIDX
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setLevelidx(Integer levelidx) {
        this.levelidx = levelidx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.FILLSTR50
     *
     * @return the value of PTMENU.FILLSTR50
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getFillstr50() {
        return fillstr50;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.FILLSTR50
     *
     * @param fillstr50 the value for PTMENU.FILLSTR50
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setFillstr50(String fillstr50) {
        this.fillstr50 = fillstr50 == null ? null : fillstr50.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.FILLSTR100
     *
     * @return the value of PTMENU.FILLSTR100
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public String getFillstr100() {
        return fillstr100;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.FILLSTR100
     *
     * @param fillstr100 the value for PTMENU.FILLSTR100
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setFillstr100(String fillstr100) {
        this.fillstr100 = fillstr100 == null ? null : fillstr100.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.FILLINT1
     *
     * @return the value of PTMENU.FILLINT1
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Long getFillint1() {
        return fillint1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.FILLINT1
     *
     * @param fillint1 the value for PTMENU.FILLINT1
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setFillint1(Long fillint1) {
        this.fillint1 = fillint1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.FILLDBL2
     *
     * @return the value of PTMENU.FILLDBL2
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public BigDecimal getFilldbl2() {
        return filldbl2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.FILLDBL2
     *
     * @param filldbl2 the value for PTMENU.FILLDBL2
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setFilldbl2(BigDecimal filldbl2) {
        this.filldbl2 = filldbl2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PTMENU.FILLDATE
     *
     * @return the value of PTMENU.FILLDATE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public Date getFilldate() {
        return filldate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PTMENU.FILLDATE
     *
     * @param filldate the value for PTMENU.FILLDATE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    public void setFilldate(Date filldate) {
        this.filldate = filldate;
    }
}