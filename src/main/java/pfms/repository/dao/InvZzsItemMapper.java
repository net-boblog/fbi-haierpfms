package pfms.repository.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pfms.repository.model.InvZzsItem;
import pfms.repository.model.InvZzsItemExample;
import pfms.repository.model.InvZzsItemKey;

public interface InvZzsItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int countByExample(InvZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int deleteByExample(InvZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int deleteByPrimaryKey(InvZzsItemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int insert(InvZzsItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int insertSelective(InvZzsItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    List<InvZzsItem> selectByExample(InvZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    InvZzsItem selectByPrimaryKey(InvZzsItemKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByExampleSelective(@Param("record") InvZzsItem record, @Param("example") InvZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByExample(@Param("record") InvZzsItem record, @Param("example") InvZzsItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByPrimaryKeySelective(InvZzsItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ITEM
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByPrimaryKey(InvZzsItem record);
}