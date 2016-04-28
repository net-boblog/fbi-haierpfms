package pfms.enums;

import java.util.Hashtable;

/**
 * 发票类别(0，表增值税专用发票 1、表增值税普通发票 2表地税发票 3表电子发票)
 */
public enum EnuZzsFpzl {
    FPZL_0("0", "增值税专用发票"),
    FPZL_1("1", "增值税普通发票"),
    FPZL_2("2", "地税发票"),
    FPZL_3("3", "电子发票");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsFpzl> aliasEnums;

    EnuZzsFpzl(String code, String title) {
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

    public static EnuZzsFpzl valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
