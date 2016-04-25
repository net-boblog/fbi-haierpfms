package pfms.enums;

import java.util.Hashtable;

/**
 * ϵͳ�����־(0�Ϳգ���ʾδ����1��ʾ�Ѵ���
 */
public enum EnuZzsZfClbz {
    ZF_CLBZ_0("0", "δ����"),
    ZF_CLBZ_1("1", "�Ѵ���");

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
