package gateway.sbs.core;

import gateway.sbs.core.domain.SOFForm;

import java.util.ArrayList;
import java.util.List;

/**
 * SBS��Ӧ
 */
public class SBSResponse {
    protected List<String> formCodes = new ArrayList<String>();
    protected List<SOFForm> sofForms = new ArrayList<SOFForm>();

    public List<String> getFormCodes() {
        return formCodes;
    }

    public List<SOFForm> getSofForms() {
        return sofForms;
    }

    // ��������
    public void init(byte[] buffer) {
        int index = 0;
        do {
            // �������Կո�ͷ������Ϊ�հ���������
            if (buffer[index] != 0x20) {
                return;
            }
            SOFForm sofForm = new SOFForm();
            sofForm.assembleFields(index, buffer);
            formCodes.add(sofForm.getFormHeader().getFormCode());
            sofForms.add(sofForm);
            index += sofForm.length;
        } while (buffer.length > index);
    }
}
