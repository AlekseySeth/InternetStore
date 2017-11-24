package filter;

import entity.user.Role;
import entity.user.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebFilter(servletNames = {"MyAccount", "Cart", "Registration", "Login"})
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
            HttpSession session = httpReq.getSession();
            String requestURI = httpReq.getRequestURI();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                if (requestURI.equals("/login") || requestURI.equals("/registration")) {
                    allowAccess(servletRequest, servletResponse, filterChain);
                }
            } else {
                httpResp.sendRedirect("/login");
            }

            if (user.getRole().equals(Role.ADMIN)) {
                httpResp.sendRedirect(getPath("admin"));
            } else if (user.getRole().equals(Role.MARKETER)) {
                httpResp.sendRedirect(getPath("marketer"));
            } else if (requestURI.equals("/cart") || requestURI.equals("/my-account")) {
                allowAccess(servletRequest, servletResponse, filterChain);
            } else {
                httpResp.sendRedirect("/my-account");
            }

        } else {
            allowAccess(servletRequest, servletResponse, filterChain);
        }
    }


    private void allowAccess(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}