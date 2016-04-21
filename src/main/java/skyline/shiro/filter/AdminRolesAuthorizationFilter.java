package skyline.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


/**
 * Filter that allows access if the current user has the roles specified by the mapped value, or denies access
 * if the user does not have all of the roles specified.
 *
 * @since 0.9
 */

@Service
@Qualifier("adminRolesAuthorizationFilter")
public class AdminRolesAuthorizationFilter extends AuthorizationFilter {

    //TODO - complete JavaDoc
	
	public AdminRolesAuthorizationFilter() {
        setLoginUrl("/admin/login");
        setUnauthorizedUrl("/admin/login");
    }

    @SuppressWarnings({"unchecked"})
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }

        Set<String> roles = CollectionUtils.asSet(rolesArray);

        return subject.hasAllRoles(roles);		
    }
    
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        Subject subject = getSubject(request, response);
        // If the subject isn't identified, redirect to login URL
        
        if (isLoginRequest(request, response)) {
        	return true;
        }
        
        if (subject.getPrincipal() == null) {
            saveRequestAndRedirectToLogin(request, response);
        } else {
            // If subject is known but not authorized, redirect to the unauthorized URL if there is one
            // If no unauthorized URL is specified, just return an unauthorized HTTP status code
            String unauthorizedUrl = getUnauthorizedUrl();
            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
            if (StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }

}
