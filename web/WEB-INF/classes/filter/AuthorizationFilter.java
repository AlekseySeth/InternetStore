package filter;

import com.sun.deploy.net.HttpResponse;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<Role, List<String>> permissions = AuthenticationService.newInstance().getPermissions();
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
            User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");

            if (user == null) {
                processIfGuest(servletRequest, servletResponse, requestURI, filterChain, permissions);
            } else  if (user.getRole().equals(Role.ADMIN)) {
                processIfAdmin(servletRequest, servletResponse, requestURI, filterChain, permissions);
            } else
            if (user.getRole().equals(Role.MARKETER)) {
                processIfMarketer(servletRequest, servletResponse, requestURI, filterChain, permissions);
            } else if (user.getRole().equals(Role.CUSTOMER)) {
                processIfCustomer(servletRequest, servletResponse, requestURI, filterChain, permissions);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void processIfAdmin(ServletRequest servletRequest, ServletResponse servletResponse, String requestURI, FilterChain filterChain, Map<Role, List<String>> permissions) throws ServletException, IOException {
        List<String> restrictedPages = permissions.get(Role.ADMIN);
        if (requestURI.equals("/my-account")) {
            ((HttpServletResponse) servletResponse).sendRedirect("/admin");
        } else if (restrictedPages.contains(requestURI)) {
            ((HttpServletResponse) servletResponse).sendRedirect("/my-account");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void processIfMarketer(ServletRequest servletRequest, ServletResponse servletResponse, String requestURI, FilterChain filterChain, Map<Role, List<String>> permissions) throws ServletException, IOException {
        List<String> restrictedPages = permissions.get(Role.MARKETER);
        if (requestURI.equals("/my-account")) {
            ((HttpServletResponse) servletResponse).sendRedirect("/marketer");
        } else if (restrictedPages.contains(requestURI)) {
            ((HttpServletResponse) servletResponse).sendRedirect("/my-account");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void processIfCustomer(ServletRequest servletRequest, ServletResponse servletResponse, String requestURI, FilterChain filterChain, Map<Role, List<String>> permissions) throws ServletException, IOException {
        List<String> restrictedPages = permissions.get(Role.CUSTOMER);
        String referer = ((HttpServletRequest) servletRequest).getHeader("Referer");
        if (restrictedPages.contains(requestURI)
                || (referer.equals("/order-placed") && requestURI.equals("/cart"))) {
            ((HttpServletResponse) servletResponse).sendRedirect("/my-account");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void processIfGuest(ServletRequest servletRequest, ServletResponse servletResponse, String requestURI, FilterChain filterChain, Map<Role, List<String>> permissions) throws IOException, ServletException {
        List<String> restrictedPages = permissions.get(Role.GUEST);
        if (restrictedPages.contains(requestURI)) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}