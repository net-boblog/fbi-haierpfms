package skyline.stp.common.enums;

import java.util.Hashtable;

/**
 * Created by XIANGYANG on 2015-8-6.
 * 操作类型
 */

public enum EnumOperateType {
    OPERATE_ADD("OPERATE_ADD", "添加"),
    OPERATE_UPD("OPERATE_UPD", "修改"),
    OPERATE_DEL_CREATE("OPERATE_DEL_CREATE", "删除创建"),
    OPERATE_DEL("OPERATE_DEL", "删除"),
    OPERATE_PAUSE("OPERATE_PAUSE", "暂停"),
    OPERATE_RESUME("OPERATE_RESUME", "恢复"),
    OPERATE_RUN_ONCE("OPERATE_RUN_ONCE", "立即运行一次"),
    OPERATE_DETAIL("OPERATE_DETAIL", "详细");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnumOperateType> aliasEnums;

    EnumOperateType(String code, String title) {
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

    public static EnumOperateType valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
