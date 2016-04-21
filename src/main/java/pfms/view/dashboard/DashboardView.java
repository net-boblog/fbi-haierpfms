package pfms.view.dashboard;

import skyline.view.AbstractBacking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.security.OperManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanrui on 2015/11/5
 */
@ManagedBean
@SessionScoped
public class DashboardView extends AbstractBacking {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String themeName = "blue";

    private List<String> breadCrumbs = new ArrayList<>();

    private String branchId;
    private String branchName;

    @ManagedProperty(value = "#{operManager}")
    private OperManager operManager;

    @PostConstruct
    public void init() {
        String operid = operManager.getOperInfo().getOperId();
        branchId = operManager.getOperInfo().getPtdept().getDeptid();
        branchName = operManager.getOperInfo().getPtdept().getDeptname();

    }


    //============


    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public List<String> getBreadCrumbs() {
        return breadCrumbs;
    }

    public void setBreadCrumbs(List<String> breadCrumbs) {
        this.breadCrumbs = breadCrumbs;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public OperManager getOperManager() {
        return operManager;
    }

    public void setOperManager(OperManager operManager) {
        this.operManager = operManager;
    }
}
