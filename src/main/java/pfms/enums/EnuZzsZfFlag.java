package pfms.enums;

import java.util.Hashtable;

/**
 * ���ϱ�־��0 δ���� 1 ������ 2 ����ʧ�ܣ�
 */
public enum EnuZzsZfFlag {
    ZF_FLAG_0("0", "δ����"),
    ZF_FLAG_1("1", "������"),
    ZF_FLAG_2("2", "����ʧ��");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnuZzsZfFlag> aliasEnums;

    EnuZzsZfFlag(String code, String title) {
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

    public static EnuZzsZfFlag valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
