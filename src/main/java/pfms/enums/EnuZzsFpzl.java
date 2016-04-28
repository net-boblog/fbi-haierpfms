package pfms.enums;

import java.util.Hashtable;

/**
 * ��Ʊ���(0������ֵ˰ר�÷�Ʊ 1������ֵ˰��ͨ��Ʊ 2���˰��Ʊ 3����ӷ�Ʊ)
 */
public enum EnuZzsFpzl {
    FPZL_0("0", "��ֵ˰ר�÷�Ʊ"),
    FPZL_1("1", "��ֵ˰��ͨ��Ʊ"),
    FPZL_2("2", "��˰��Ʊ"),
    FPZL_3("3", "���ӷ�Ʊ");

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
