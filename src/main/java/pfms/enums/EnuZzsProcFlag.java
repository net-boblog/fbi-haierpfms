package pfms.enums;

import java.util.Hashtable;

/**
 * 增值税原始数据表中的处理标志
 */
public enum EnuZzsProcFlag {
    PROC_FLAG_0("0", "未处理"),
    PROC_FLAG_1("1", "已处理");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsProcFlag> aliasEnums;

    EnuZzsProcFlag(String code, String title) {
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

    public static EnuZzsProcFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
