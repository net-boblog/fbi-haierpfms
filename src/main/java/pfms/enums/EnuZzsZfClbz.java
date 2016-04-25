package pfms.enums;

import java.util.Hashtable;

/**
 * 系统处理标志(0和空，表示未处理，1表示已处理）
 */
public enum EnuZzsZfClbz {
    ZF_CLBZ_0("0", "未处理"),
    ZF_CLBZ_1("1", "已处理");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsZfClbz> aliasEnums;

    EnuZzsZfClbz(String code, String title) {
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

    public static EnuZzsZfClbz valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
