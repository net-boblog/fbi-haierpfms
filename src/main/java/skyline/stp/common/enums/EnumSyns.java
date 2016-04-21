package skyline.stp.common.enums;

import java.util.Hashtable;

/**
 * Created by XIANGYANG on 2015-8-6.
 */

public enum EnumSyns {
    SYNS("1", "Õ¨≤Ω"),
    UNSYNS("0", "“Ï≤Ω");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnumSyns> aliasEnums;

    EnumSyns(String code, String title) {
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

    public static EnumSyns valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
