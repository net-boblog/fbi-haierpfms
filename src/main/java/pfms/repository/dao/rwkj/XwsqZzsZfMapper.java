package pfms.repository.dao.rwkj;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pfms.repository.model.rwkj.XwsqZzsZf;
import pfms.repository.model.rwkj.XwsqZzsZfExample;
import pfms.repository.model.rwkj.XwsqZzsZfKey;

public interface XwsqZzsZfMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int countByExample(XwsqZzsZfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int deleteByExample(XwsqZzsZfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int deleteByPrimaryKey(XwsqZzsZfKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int insert(XwsqZzsZf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int insertSelective(XwsqZzsZf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    List<XwsqZzsZf> selectByExample(XwsqZzsZfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    XwsqZzsZf selectByPrimaryKey(XwsqZzsZfKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") XwsqZzsZf record, @Param("example") XwsqZzsZfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByExample(@Param("record") XwsqZzsZf record, @Param("example") XwsqZzsZfExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByPrimaryKeySelective(XwsqZzsZf record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    int updateByPrimaryKey(XwsqZzsZf record);
}