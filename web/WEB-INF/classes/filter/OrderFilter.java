package filter;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author a.shestovsky
 */
@WebFilter("/*")
public class OrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            User user = (User) session.getAttribute("user");
            String referer = ((HttpServletRequest) servletRequest).getHeader("Referer");

            if (requestURI.equals("/order-placed") && session.getAttribute("isPlaced") == null) {
                ((HttpServletResponse) servletResponse).sendRedirect(referer);
            } else if (requestURI.equals("/order-placed") && session.getAttribute("isPlaced").equals("true")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }

            if (referer.equals("/order-placed")) {
                session.setAttribute("order", AuthenticationService.newInstance().createInitialOrder(user));
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}