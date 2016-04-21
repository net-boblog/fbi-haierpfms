package skyline.security;

import skyline.service.OperInfo;
import skyline.service.PlatformService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import skyline.common.PropertyManager;

/**
 * Created by zhanrui on 2015/11/8.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OperManager {
    private OperInfo operInfo;
    private boolean isLogined;
    private String mailbox;
    private String loginTime;
    private String browserInfo;

    @Autowired
    private PlatformService platformService;


    public int login(UsernamePasswordToken token) {
        String usernameTmp = token.getUsername();
        String passwordTmp = token.getPassword();
        operInfo = platformService.initOperInfo(usernameTmp);
        if(operInfo==null){
            return -1;
        }else {
            if (!"1".equals(PropertyManager.getProperty("development"))) {
                if (!platformService.getCipherPassword(usernameTmp, passwordTmp).equals(operInfo.getPtoper().getOperpasswd())) {
                    return -1;
                }
            }
        }
        //验证通过
        this.isLogined = true;
        this.loginTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        return 0;
    }

    //==================================
    public OperInfo getOperInfo() {
        return operInfo;
    }

    public void setOperInfo(OperInfo operInfo) {
        this.operInfo = operInfo;
    }

    public String getMailbox() {
        return mailbox;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public boolean isLogined() {
        return isLogined;
    }
}
