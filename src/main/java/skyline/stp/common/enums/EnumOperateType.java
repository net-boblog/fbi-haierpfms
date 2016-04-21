package skyline.stp.common.enums;

import java.util.Hashtable;

/**
 * Created by XIANGYANG on 2015-8-6.
 * ��������
 */

public enum EnumOperateType {
    OPERATE_ADD("OPERATE_ADD", "���"),
    OPERATE_UPD("OPERATE_UPD", "�޸�"),
    OPERATE_DEL_CREATE("OPERATE_DEL_CREATE", "ɾ������"),
    OPERATE_DEL("OPERATE_DEL", "ɾ��"),
    OPERATE_PAUSE("OPERATE_PAUSE", "��ͣ"),
    OPERATE_RESUME("OPERATE_RESUME", "�ָ�"),
    OPERATE_RUN_ONCE("OPERATE_RUN_ONCE", "��������һ��"),
    OPERATE_DETAIL("OPERATE_DETAIL", "��ϸ");

    private String code = null;
    private String title = null;
    private static Hashtable<String, EnumOperateType> aliasEnums;

    EnumOperateType(String code, String title) {
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

    public static EnumOperateType valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
