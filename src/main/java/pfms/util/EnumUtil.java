package pfms.util;

import pfms.enums.EnuZzsFpzl;
import pfms.enums.EnuZzsSrc;

import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * 枚举工具类
 */
public class EnumUtil {
    /**
     * 取得数据来源下拉列表
     *
     * @return
     */
    public static List<SelectItem> getSrcList() {
        List<SelectItem> zzsSrcList = new ArrayList<SelectItem>();
        zzsSrcList.add(new SelectItem("", "请选择"));
        for (EnuZzsSrc zzsSrc : EnuZzsSrc.values()) {
            zzsSrcList.add(new SelectItem(zzsSrc.getCode(), zzsSrc.getTitle()));
        }
        return zzsSrcList;
    }

    /**
     * 取得发票类别下拉列表
     *
     * @return
     */
    public static List<SelectItem> getFpzlList() {
        List<SelectItem> zzsFpzlList = new ArrayList<SelectItem>();
        zzsFpzlList.add(new SelectItem("", "请选择"));
        for (EnuZzsFpzl zzsFpzl : EnuZzsFpzl.values()) {
            zzsFpzlList.add(new SelectItem(zzsFpzl.getCode(), zzsFpzl.getTitle()));
        }
        return zzsFpzlList;
    }
}
