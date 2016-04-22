package skyline.repository.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import skyline.repository.model.Ptrole;
import skyline.repository.model.PtroleExample;

public interface PtroleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int countByExample(PtroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int deleteByExample(PtroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int deleteByPrimaryKey(String roleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int insert(Ptrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int insertSelective(Ptrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    List<Ptrole> selectByExample(PtroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    Ptrole selectByPrimaryKey(String roleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int updateByExampleSelective(@Param("record") Ptrole record, @Param("example") PtroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int updateByExample(@Param("record") Ptrole record, @Param("example") PtroleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int updateByPrimaryKeySelective(Ptrole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTROLE
     *
     * @mbggenerated Tue Jan 12 13:50:41 AWST 2016
     */
    int updateByPrimaryKey(Ptrole record);
}