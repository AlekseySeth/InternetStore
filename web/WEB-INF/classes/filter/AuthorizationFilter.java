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

            processIfNotLoggedIn(servletRequest, servletResponse, filterChain, httpResp, requestURI, user);
            processIfAdmin(servletRequest, servletResponse, httpReq, httpResp, requestURI, user);
            processIfMarketer(servletRequest, servletResponse, httpReq, httpResp, requestURI, user);
            processIfCustomer(servletRequest, servletResponse, filterChain, httpResp, requestURI);

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void processIfCustomer(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, HttpServletResponse httpResp, String requestURI) throws IOException, ServletException {
        if (requestURI.equals("/cart") || requestURI.equals("/my-account")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResp.sendRedirect("/my-account");
        }
    }

    private void processIfMarketer(ServletRequest servletRequest, ServletResponse servletResponse, HttpServletRequest httpReq, HttpServletResponse httpResp, String requestURI, User user) throws ServletException, IOException {
        if (user.getRole().equals(Role.MARKETER)) {
            if (requestURI.equals("/my-account")) {
                httpReq.getRequestDispatcher(getPath("marketer")).forward(servletRequest, servletResponse);
            } else {
                httpResp.sendRedirect("/my-account");
            }
        }
    }

    private void processIfAdmin(ServletRequest servletRequest, ServletResponse servletResponse, HttpServletRequest httpReq, HttpServletResponse httpResp, String requestURI, User user) throws ServletException, IOException {
        if (user.getRole().equals(Role.ADMIN)) {
            if (requestURI.equals("/my-account")) {
                httpReq.getRequestDispatcher(getPath("admin")).forward(servletRequest, servletResponse);
            } else {
                httpResp.sendRedirect("/my-account");
            }
        }
    }

    private void processIfNotLoggedIn(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, HttpServletResponse httpResp, String requestURI, User user) throws IOException, ServletException {
        if (user == null) {
            if (requestURI.equals("/login") || requestURI.equals("/registration")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            httpResp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {}
}