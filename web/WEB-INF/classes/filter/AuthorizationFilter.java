package filter;

import entity.user.Role;
import entity.user.User;
import service.AuthenticationService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebFilter(servletNames = {"MyAccount", "Cart", "Registration", "Login"})
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Map<Role, List<String>> permissions = AuthenticationService.newInstance().getPermissions();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
            User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");

            if (user == null) {
                processIfNotLogged(servletRequest, servletResponse, filterChain, requestURI);
            } else  if (user.getRole().equals(Role.ADMIN)) {
                processIfAdmin(servletRequest, servletResponse, requestURI);
            } else
            if (user.getRole().equals(Role.MARKETER)) {
                processIfMarketer(servletRequest, servletResponse, requestURI);
            } else if (user.getRole().equals(Role.CUSTOMER)) {
                processIfCustomer(servletRequest, servletResponse, requestURI);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void processIfMarketer(ServletRequest servletRequest, ServletResponse servletResponse, String requestURI) throws ServletException, IOException {
        if (requestURI.equals("/my-account")) {
            servletRequest.getRequestDispatcher(getPath("marketer")).forward(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/my-account");
        }
    }

    private void processIfAdmin(ServletRequest servletRequest, ServletResponse servletResponse, String requestURI) throws ServletException, IOException {
        if (requestURI.equals("/my-account")) {
            servletRequest.getRequestDispatcher(getPath("admin")).forward(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/my-account");
        }
    }

    private void processIfCustomer(ServletRequest servletRequest, ServletResponse servletResponse, String requestURI) throws ServletException, IOException {
        if (requestURI.equals("/my-account")) {
            servletRequest.getRequestDispatcher(getPath("admin")).forward(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/my-account");
        }
    }

    private void processIfNotLogged(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, String requestURI) throws IOException, ServletException {
        if (requestURI.equals("/login") || requestURI.equals("/registration")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {}
}