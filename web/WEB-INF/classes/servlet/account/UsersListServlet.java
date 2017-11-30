package servlet.account;

import entity.user.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/users-list", name = "UsersList")
public class UsersListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.newInstance().getUsersList();
        req.setAttribute("users", users);
        req.getServletContext().getRequestDispatcher(getPath("users-list")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("findBy");
        if (parameter.equals("email")) {
            User userByEmail = UserService.newInstance().getUserByEmail(parameter);
            resp.sendRedirect("/user?id=" + userByEmail.getId());
        } else if (parameter.equals("id")) {
            Long id = Long.valueOf(parameter);
            resp.sendRedirect("/user?id=" + id);
        }
    }
}
