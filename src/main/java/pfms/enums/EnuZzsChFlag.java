package pfms.enums;

import java.util.Hashtable;

/**
 * ≥Â∫Ï±Í÷æ£®0 Œ¥≥Â∫Ï 1 “—≥Â∫Ï 2 ≥Â∫Ï ß∞‹£©
 */
public enum EnuZzsChFlag {
    ZF_FLAG_0("0", "Œ¥≥Â∫Ï"),
    ZF_FLAG_1("1", "“—≥Â∫Ï"),
    ZF_FLAG_2("2", "≥Â∫Ï ß∞‹");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsChFlag> aliasEnums;

    EnuZzsChFlag(String code, String title) {
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

    public static EnuZzsChFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
