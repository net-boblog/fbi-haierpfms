package skyline.view.uitemplate;

import skyline.repository.model.Ptmenu;
import skyline.service.PlatformService;
import skyline.view.AbstractBacking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.common.primefaces.MessageUtil;
import skyline.security.OperManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhanrui on 2015/11/5.
 */
@ManagedBean
@SessionScoped
public class UiTemplateView extends AbstractBacking {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Ptmenu> topLevelMenuList = new ArrayList<>();
    private Map<String, List<Ptmenu>> secondLevelMenuMap = new HashMap<>();

    private List<String> breadCrumbs = new ArrayList<>();

    private String operId;
    private String operName;
    private String branchId;
    private String branchName;
    private List<String> roleNames;

    @ManagedProperty(value = "#{operManager}")
    private OperManager operManager;
    @ManagedProperty(value = "#{platformService}")
    private PlatformService platformService;

    @PostConstruct
    public void init() {
        operId = operManager.getOperInfo().getOperId();
        operName = operManager.getOperInfo().getOperName();
        branchId = operManager.getOperInfo().getPtdept().getDeptid();
        branchName = operManager.getOperInfo().getPtdept().getDeptname();
        //roleNames = operManager.getOperInfo().getPtRoles().stream().map(THrmRolemanage::getRoledesc).collect(Collectors.toList());
        initMenu();
        initBreadCrumbs();
    }

    private void initMenu() {
        List<Ptmenu> menuList = platformService.selectMenuList(operManager.getOperInfo().getOperId());

        this.topLevelMenuList =
                menuList.stream().filter(m -> m.getMenulevel() == 1).collect(Collectors.toList());
        this.secondLevelMenuMap =
                menuList.stream().filter(m -> m.getIsleaf() == 1).collect(Collectors.groupingBy(Ptmenu::getParentmenuid));
    }


    public void onClickMenuItem(Ptmenu menu) {
        try {
            //根据当前选择的菜单处理breadcrumb
            List<Ptmenu> menus = new ArrayList<>();
            makeMenuBreadCrumbs(menus, menu);
            Collections.reverse(menus);
            initBreadCrumbs();
            for (Ptmenu Ptmenu : menus) {
                this.breadCrumbs.add(Ptmenu.getMenulabel());
            }

            //会话ID跳转处理
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String action = menu.getMenuaction();
            ec.redirect(ec.getRequestContextPath() + action);

            //更新常用菜单
//            loginManager.updateMostUsedmenu(menu);
        } catch (Exception e) {
            logger.error("页面导航错误", e);
            MessageUtil.addError("页面导航错误");
        }
    }

    public List<Ptmenu> getMostUsedMenus() {
//        List<Ptmenu>
        return null;
    }


    private void initBreadCrumbs() {
        this.breadCrumbs = new ArrayList<>();
        this.breadCrumbs.add("工作台");
//        this.breadCrumbs.add(ptmodule.getModuleLabel());
    }

    private void makeMenuBreadCrumbs(List<Ptmenu> menus, Ptmenu menu) {
        menus.add(menu);
        if (menu.getMenulevel() != 1) {//不是一级菜单
            Ptmenu parentMenu =
                    this.topLevelMenuList.stream().filter(m -> m.getMenuid().equals(menu.getParentmenuid())).findFirst().get();
            makeMenuBreadCrumbs(menus, parentMenu);
        }
//        else {
//            String m = menu.getTargetmachine();
//            if (!this.moduleName.equalsIgnoreCase(m)) {
//                this.moduleName = m;
//                initMenu();
//                initBreadCrumbs();
//            }
//        }
    }


    //========

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

    public List<Ptmenu> getTopLevelMenuList() {
        return topLevelMenuList;
    }

    public void setTopLevelMenuList(List<Ptmenu> topLevelMenuList) {
        this.topLevelMenuList = topLevelMenuList;
    }

    public Map<String, List<Ptmenu>> getSecondLevelMenuMap() {
        return secondLevelMenuMap;
    }

    public void setSecondLevelMenuMap(Map<String, List<Ptmenu>> secondLevelMenuMap) {
        this.secondLevelMenuMap = secondLevelMenuMap;
    }

    public PlatformService getPlatformService() {
        return platformService;
    }

    public void setPlatformService(PlatformService platformService) {
        this.platformService = platformService;
    }

    public OperManager getOperManager() {
        return operManager;
    }

    public void setOperManager(OperManager operManager) {
        this.operManager = operManager;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }
}
