package pfms.enums;

import java.util.Hashtable;

/**
 * 类型 1：作废  2 冲红
 */
public enum EnuZzsZfType {
    ZF_TYPE_1("1", "作废"),
    ZF_TYPE_2("2", "冲红");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsZfType> aliasEnums;

    EnuZzsZfType(String code, String title) {
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

    public static EnuZzsZfType valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
