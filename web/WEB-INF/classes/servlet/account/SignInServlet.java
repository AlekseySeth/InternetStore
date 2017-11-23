package servlet.account;

import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import entity.order.Order;
import entity.user.User;
import service.AuthorizationService;

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
@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext()
                .getRequestDispatcher(getPath("sign-in"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        AuthorizationService authorizationService = AuthorizationService.newInstance();
        User user = authorizationService.signIn(email, password);

        if (user != null) {
            Order initialOrder = authorizationService.createInitialOrder(user);
            session.setAttribute("user", user);
            session.setAttribute("order", initialOrder);
        }
    }
}