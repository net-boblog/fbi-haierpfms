package pfms.enums;

import java.util.Hashtable;

/**
 * 增值税原始数据表中的打印发票标志
 */
public enum EnuZzsPrintFlag {
    PRINT_FLAG_0("0", "不开发票"),
    PRINT_FLAG_1("1", "开发票");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsPrintFlag> aliasEnums;

    EnuZzsPrintFlag(String code, String title) {
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

    public static EnuZzsPrintFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
