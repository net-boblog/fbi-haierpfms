package skyline.view.login;

import org.apache.commons.lang3.StringUtils;
import skyline.service.OperInfo;
import skyline.service.PlatformService;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import skyline.common.primefaces.MessageUtil;
import skyline.security.OperManager;
import skyline.security.UsernamePasswordToken;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

/**
 * User: zhanrui
 * Date: 2015-11-11
 */
@ManagedBean
@ViewScoped
public class LoginView implements Serializable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String operId;
    private String operPasswd;
    private String operNewPasswd1;
    private String operNewPasswd2;
    private boolean agree = true;


    @ManagedProperty(value = "#{operManager}")
    private OperManager operManager;
    @ManagedProperty(value = "#{platformService}")
    private PlatformService platformService;


    @PostConstruct
    private void init() {
        if (operManager.isLogined()) {
            logger.info("“—µ«¬º°£");
        }
    }

    public String onLogin() {
        try {
            if (StringUtils.isBlank(operId)) {
                MessageUtil.addWarn("«Î ‰»Î”√ªß√˚.");
                return null;
            }
            if (StringUtils.isBlank(operPasswd)) {
                MessageUtil.addWarn("«Î ‰»Î”√ªß√‹¬Î.");
                return null;
            }
            int result = operManager.login(new UsernamePasswordToken(operId, operPasswd, null));
            if (result < 0) {
                MessageUtil.addWarn("∏√”√ªß≤ª¥Ê‘⁄ªÚ√‹¬Î¥ÌŒÛ.");
                return null;
            }
            return "/ui/backend/dashboard?faces-redirect=true";
        } catch (Exception e) {
            logger.error("µ«¬º¥ÌŒÛ°£", e);
            MessageUtil.addWarn("œµÕ≥µ«¬º¥ÌŒÛ:" + e.getMessage());
        }
        return null;
    }

    public String onLogout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(false);
        session.invalidate();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        try {
            response.sendRedirect(((HttpServletRequest) context.getRequest()).getContextPath() + "/ui/backend/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        //return "/ui/backend/login?faces-redirect=true";
    }

    public String onChangePassword() {
        if (operPasswd == null || "".equals(operPasswd)) {
            MessageUtil.addWarn("«Î ‰»Îæ…√‹¬Î.");
            return null;
        }

        OperInfo operInfo = operManager.getOperInfo();
        String cipherPassword = platformService.getCipherPassword(operInfo.getOperId(), operPasswd);
        String dbPassword = operInfo.getPtoper().getOperpasswd().trim();
        if (!cipherPassword.equals(dbPassword)) {
            MessageUtil.addWarn("√‹¬Î¥ÌŒÛ...");
            return null;
        }
        if (!operNewPasswd1.equals(operNewPasswd2)) {
            MessageUtil.addWarn("¡Ω¥Œ√‹¬Î ‰»Î≤ª∑˚...");
            return null;
        }

        //∏¸–¬√‹¬Î
        try {
            platformService.changeOperPassword(operInfo.getOperId(), operNewPasswd1);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('operPassword').hide();");
        } catch (DataAccessException e) {
            logger.error("–ﬁ∏ƒ√‹¬Î ß∞‹°£", e);
            MessageUtil.addWarn("–ﬁ∏ƒ√‹¬Î ß∞‹°£" + e.getMessage());
        }
        return null;
    }

    //=========================

    private HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    //=========================

    public String getOperId() {
        return operId;
    }


    public String getOperPasswd() {
        return operPasswd;
    }

    public String getOperNewPasswd1() {
        return operNewPasswd1;
    }

    public String getOperNewPasswd2() {
        return operNewPasswd2;
    }

    public boolean isAgree() {
        return agree;
    }


    public void setOperId(String operId) {
        this.operId = operId;
    }

    public void setOperPasswd(String operPasswd) {
        this.operPasswd = operPasswd;
    }

    public void setOperNewPasswd1(String operNewPasswd1) {
        this.operNewPasswd1 = operNewPasswd1;
    }

    public void setOperNewPasswd2(String operNewPasswd2) {
        this.operNewPasswd2 = operNewPasswd2;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }


    public OperManager getOperManager() {
        return operManager;
    }

    public void setOperManager(OperManager operManager) {
        this.operManager = operManager;
    }

    public PlatformService getPlatformService() {
        return platformService;
    }

    public void setPlatformService(PlatformService platformService) {
        this.platformService = platformService;
    }
}
