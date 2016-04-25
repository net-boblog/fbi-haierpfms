package pfms.enums;

import java.util.Hashtable;

/**
 * 增值税原始数据表中的数据来源
 */
public enum EnuZzsSrc {
    SRC_00("00","SBS"),
    SRC_01("01","国结"),
    SRC_02("02","信贷");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsSrc> aliasEnums;

    EnuZzsSrc(String code, String title) {
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

    public static EnuZzsSrc valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
