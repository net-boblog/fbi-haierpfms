package pfms.repository.dao.custom;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;
import pfms.repository.model.custom.CustomInvZzsHead;
import pfms.repository.model.custom.CustomInvZzsSrc;

import java.util.List;

/**
 * 自定义Mapper
 */
@Component
public interface CustomMapper {
    /**
     * 查询待处理数据
     *
     * @param customInvZzsSrc
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectUnProc")
    List<CustomInvZzsSrc> selectUnProc(@Param("customInvZzsSrc") CustomInvZzsSrc customInvZzsSrc);

    /**
     * 查询不开票的数据
     *
     * @param customInvZzsSrc
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectNoPrint")
    List<CustomInvZzsSrc> selectNoPrint(@Param("customInvZzsSrc") CustomInvZzsSrc customInvZzsSrc);

    /**
     * 查询待开票数据
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectUnPrint")
    List<CustomInvZzsHead> selectUnPrint(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * 查询已开票数据
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectPrint")
    List<CustomInvZzsHead> selectPrint(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * 查询已开票失败数据
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectPrintFail")
    List<CustomInvZzsHead> selectPrintFail(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * 查询待作废数据
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectUnZuoFei")
    List<CustomInvZzsHead> selectUnZuoFei(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * 查询已作废数据
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectZuoFei")
    List<CustomInvZzsHead> selectZuoFei(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);
}
