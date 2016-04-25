package pfms.enums;

import java.util.Hashtable;

/**
 * 开票标志（0 未开票 1 已开票 2 开票失败）
 */
public enum EnuZzsKpFlag {
    KP_FLAG_0("0", "未开票"),
    KP_FLAG_1("1", "已开票"),
    KP_FLAG_2("2", "开票失败");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsKpFlag> aliasEnums;

    EnuZzsKpFlag(String code, String title) {
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

    public static EnuZzsKpFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
