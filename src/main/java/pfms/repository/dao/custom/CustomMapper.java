package pfms.repository.dao.custom;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;
import pfms.repository.model.custom.CustomInvZzsHead;
import pfms.repository.model.custom.CustomInvZzsSrc;

import java.util.List;

/**
 * �Զ���Mapper
 */
@Component
public interface CustomMapper {
    /**
     * ��ѯ����������
     *
     * @param customInvZzsSrc
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectUnProc")
    List<CustomInvZzsSrc> selectUnProc(@Param("customInvZzsSrc") CustomInvZzsSrc customInvZzsSrc);

    /**
     * ��ѯ����Ʊ������
     *
     * @param customInvZzsSrc
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectNoPrint")
    List<CustomInvZzsSrc> selectNoPrint(@Param("customInvZzsSrc") CustomInvZzsSrc customInvZzsSrc);

    /**
     * ��ѯ����Ʊ����
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectUnPrint")
    List<CustomInvZzsHead> selectUnPrint(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * ��ѯ�ѿ�Ʊ����
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectPrint")
    List<CustomInvZzsHead> selectPrint(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * ��ѯ�ѿ�Ʊʧ������
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectPrintFail")
    List<CustomInvZzsHead> selectPrintFail(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * ��ѯ����������
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectUnZuoFei")
    List<CustomInvZzsHead> selectUnZuoFei(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);

    /**
     * ��ѯ����������
     *
     * @param customInvZzsHead
     * @return
     */
    @SelectProvider(type = CustomProvider.class, method = "selectZuoFei")
    List<CustomInvZzsHead> selectZuoFei(@Param("customInvZzsHead") CustomInvZzsHead customInvZzsHead);
}
