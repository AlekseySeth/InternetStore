package servlet.account;

import entity.user.User;
import service.UserService;

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
@WebServlet(urlPatterns = "/update-password")
public class UpdatePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<p id=\"message\">Пароль успешно изменен</p>");

        getServletContext().getRequestDispatcher(getPath("update-password")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String newPassword = req.getParameter("newPassword");
        String confirmNewPassword = req.getParameter("confirmNewPassword");
        User user = (User) req.getSession().getAttribute("user");

        if (newPassword.equals(confirmNewPassword)
                && UserService.newInstance().updatePassword(user, newPassword)) {
            resp.sendRedirect("/my-account");
        } else {
            resp.sendRedirect("/update-password");
        }
    }
}