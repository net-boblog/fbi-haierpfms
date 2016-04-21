package skyline.stp.common.enums;

import javax.faces.bean.ManagedBean;
import java.util.Hashtable;

/**
 * Created by XIANGYANG on 2015-8-6.
 * 任务调度类型
 */

public enum EnumScheduleType {
    SCHEDULE_NORMAL("SCHEDULE_NORMAL", "正常调度"),
    SCHEDULE_RUN_ONCE("SCHEDULE_RUN_ONCE", "立即运行一次");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnumScheduleType> aliasEnums;

    EnumScheduleType(String code, String title) {
        this.init(code, title);
    }

    @SuppressWarnings("unchecked")
    private void init(String code, String title) {
        this.code = code;
        this.title = title;
        synchronized (this.getClass()) {
            if (aliasEnums == null) {
                aliasEnums = new Hashtable();
            }
        }
        aliasEnums.put(code, this);
        aliasEnums.put(title, this);
    }

    public static EnumScheduleType valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
