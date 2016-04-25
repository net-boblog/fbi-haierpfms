package pfms.enums;

import java.util.Hashtable;

/**
 * 作废标志（0 未作废 1 已作废 2 作废失败）
 */
public enum EnuZzsZfFlag {
    ZF_FLAG_0("0", "未作废"),
    ZF_FLAG_1("1", "已作废"),
    ZF_FLAG_2("2", "作废失败");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsZfFlag> aliasEnums;

    EnuZzsZfFlag(String code, String title) {
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

    public static EnuZzsZfFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
