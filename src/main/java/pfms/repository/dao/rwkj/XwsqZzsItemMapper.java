package pfms.repository.dao.rwkj;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pfms.repository.model.rwkj.XwsqZzsItem;
import pfms.repository.model.rwkj.XwsqZzsItemExample;
import pfms.repository.model.rwkj.XwsqZzsItemKey;

public interface XwsqZzsItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int countByExample(XwsqZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int deleteByExample(XwsqZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int deleteByPrimaryKey(XwsqZzsItemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int insert(XwsqZzsItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int insertSelective(XwsqZzsItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    List<XwsqZzsItem> selectByExample(XwsqZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    XwsqZzsItem selectByPrimaryKey(XwsqZzsItemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") XwsqZzsItem record, @Param("example") XwsqZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByExample(@Param("record") XwsqZzsItem record, @Param("example") XwsqZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByPrimaryKeySelective(XwsqZzsItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ITEM
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByPrimaryKey(XwsqZzsItem record);
}