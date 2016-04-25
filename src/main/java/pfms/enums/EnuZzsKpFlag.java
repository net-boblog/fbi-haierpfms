package pfms.enums;

import java.util.Hashtable;

/**
 * ��Ʊ��־��0 δ��Ʊ 1 �ѿ�Ʊ 2 ��Ʊʧ�ܣ�
 */
public enum EnuZzsKpFlag {
    KP_FLAG_0("0", "δ��Ʊ"),
    KP_FLAG_1("1", "�ѿ�Ʊ"),
    KP_FLAG_2("2", "��Ʊʧ��");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsKpFlag> aliasEnums;

    EnuZzsKpFlag(String code, String title) {
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

    public static EnuZzsKpFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
