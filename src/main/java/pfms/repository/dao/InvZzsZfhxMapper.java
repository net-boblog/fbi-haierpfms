package pfms.repository.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pfms.repository.model.InvZzsZfhx;
import pfms.repository.model.InvZzsZfhxExample;

public interface InvZzsZfhxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int countByExample(InvZzsZfhxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int deleteByExample(InvZzsZfhxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int deleteByPrimaryKey(String invoicecode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int insert(InvZzsZfhx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int insertSelective(InvZzsZfhx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    List<InvZzsZfhx> selectByExample(InvZzsZfhxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    InvZzsZfhx selectByPrimaryKey(String invoicecode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByExampleSelective(@Param("record") InvZzsZfhx record, @Param("example") InvZzsZfhxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByExample(@Param("record") InvZzsZfhx record, @Param("example") InvZzsZfhxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByPrimaryKeySelective(InvZzsZfhx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    int updateByPrimaryKey(InvZzsZfhx record);
}