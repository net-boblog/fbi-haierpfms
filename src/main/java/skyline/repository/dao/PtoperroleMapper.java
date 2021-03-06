package skyline.repository.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import skyline.repository.model.Ptoperrole;
import skyline.repository.model.PtoperroleExample;

public interface PtoperroleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTOPERROLE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    int countByExample(PtoperroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTOPERROLE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    int deleteByExample(PtoperroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTOPERROLE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    int insert(Ptoperrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTOPERROLE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    int insertSelective(Ptoperrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTOPERROLE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    List<Ptoperrole> selectByExample(PtoperroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTOPERROLE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    int updateByExampleSelective(@Param("record") Ptoperrole record, @Param("example") PtoperroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTOPERROLE
     *
     * @mbggenerated Tue Jan 12 13:20:41 CST 2016
     */
    int updateByExample(@Param("record") Ptoperrole record, @Param("example") PtoperroleExample example);
}