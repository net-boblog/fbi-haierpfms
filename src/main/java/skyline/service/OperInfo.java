package skyline.service;


import skyline.repository.model.Ptdept;
import skyline.repository.model.Ptoper;
import skyline.repository.model.Ptoperrole;

import java.util.List;

/**
 * Created by zhanrui on 2015/11/5.
 */
public class OperInfo {
    String operId;
    String operName;
    Ptoper ptoper;
    Ptdept ptdept;
    List<Ptoperrole> ptRoles;

    //===getter==============
    public String getOperId() {
        return operId;
    }

    public String getOperName() {
        return operName;
    }

    public Ptoper getPtoper() {
        return ptoper;
    }

    public Ptdept getPtdept() {
        return ptdept;
    }

    public List<Ptoperrole> getPtRoles() {
        return ptRoles;
    }
}
