package pfms.enums;

import java.util.Hashtable;

/**
 * ��ֵ˰ԭʼ���ݱ��еĴ�ӡ��Ʊ��־
 */
public enum EnuZzsPrintFlag {
    PRINT_FLAG_0("0", "������Ʊ"),
    PRINT_FLAG_1("1", "����Ʊ");

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
