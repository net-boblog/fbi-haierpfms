package pfms.enums;

import java.util.Hashtable;

/**
 * 是否是一般纳税人（0：否；1：是）
 */
public enum EnuZzsYbnsrFlag {
    YBNSR_FLAG_0("0", "否"),
    YBNSR_FLAG_1("1", "是");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsYbnsrFlag> aliasEnums;

    EnuZzsYbnsrFlag(String code, String title) {
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

    public static EnuZzsYbnsrFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
