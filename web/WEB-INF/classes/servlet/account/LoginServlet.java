package servlet.account;

import entity.user.Role;
import entity.user.User;
import service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/login", name = "Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext()
                .getRequestDispatcher(getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        AuthenticationService authenticationService = AuthenticationService.newInstance();
        password = authenticationService.encryptPassword(email, password);
        User user = authenticationService.signIn(email, password);

        if (user != null) {
            session.setAttribute("user", user);
            if (user.getRole().equals(Role.CUSTOMER)) {
                session.setAttribute("order", authenticationService.createInitialOrder(user));
                session.setAttribute("isPlaced", false);
            }
            resp.sendRedirect("/my-account");
        } else {
            resp.sendRedirect("/login");
        }
    }
}