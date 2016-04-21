package skyline.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skyline.security.OperManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhanrui on 2015/11/15.
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = {"*.xhtml"})
public class LoginCheckFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            String uri = req.getRequestURI();

            if (uri.contains("/logout.xhtml") && session != null) {
                OperManager om = (OperManager) req.getSession().getAttribute("scopedTarget.operManager");
                if (om != null && om.isLogined()) {
                    session.invalidate();
                    resp.sendRedirect(req.getContextPath() + "/ui/backend/login.xhtml");
                    return;
                }
            }

            if (uri.contains("/login.xhtml")
                    || session == null
                    || uri.contains("/ui/backend/pages/")
                    || uri.contains("javax.faces.resource")) {
                chain.doFilter(request, response);
            } else {
                OperManager om = (OperManager) req.getSession().getAttribute("scopedTarget.operManager");
                if (om != null && om.isLogined()) {
                    chain.doFilter(request, response);
                } else{
                    logger.info("未登录的请求:" + uri);
                    resp.sendRedirect(req.getContextPath() + "/ui/backend/login.xhtml");
                }
            }
        } catch (Exception e) {
            logger.error("认证错误", e);
        }
    }

    @Override
    public void destroy() {

    }
}
