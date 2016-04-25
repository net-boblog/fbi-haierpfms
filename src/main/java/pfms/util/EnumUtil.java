package pfms.util;

import pfms.enums.EnuZzsFpzl;
import pfms.enums.EnuZzsSrc;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * ö�ٹ�����
 */
public class EnumUtil {
    /**
     * ȡ��������Դ�����б�
     *
     * @return
     */
    public static List<SelectItem> getSrcList() {
        List<SelectItem> zzsSrcList = new ArrayList<SelectItem>();
        zzsSrcList.add(new SelectItem("", "��ѡ��"));
        for (EnuZzsSrc zzsSrc : EnuZzsSrc.values()) {
            zzsSrcList.add(new SelectItem(zzsSrc.getCode(), zzsSrc.getTitle()));
        }
        return zzsSrcList;
    }

    /**
     * ȡ�÷�Ʊ��������б�
     *
     * @return
     */
    public static List<SelectItem> getFpzlList() {
        List<SelectItem> zzsFpzlList = new ArrayList<SelectItem>();
        zzsFpzlList.add(new SelectItem("", "��ѡ��"));
        for (EnuZzsFpzl zzsFpzl : EnuZzsFpzl.values()) {
            zzsFpzlList.add(new SelectItem(zzsFpzl.getCode(), zzsFpzl.getTitle()));
        }
        return zzsFpzlList;
    }
}
