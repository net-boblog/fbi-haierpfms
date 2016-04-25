package pfms.enums;

import java.util.Hashtable;

/**
 * ��ֵ˰ԭʼ���ݱ��е�������Դ
 */
public enum EnuZzsSrc {
    SRC_00("00","SBS"),
    SRC_01("01","����"),
    SRC_02("02","�Ŵ�");

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
