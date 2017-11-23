package servlet.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//        получает email & password и вызывает AuthorizationService.signIn если != null то createInitialOrder
//        затем записывает данные user и order в сессию
    }
}
