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
@WebServlet(urlPatterns = "/update-user", name = "UpdateUser")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        User user = UserService.newInstance().getUserById(userId);
        req.setAttribute("user", user);
        req.getServletContext().getRequestDispatcher(getPath("update-user")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String newPassword = req.getParameter("newPassword");
        String confirmNewPassword = req.getParameter("confirmNewPassword");
        Long userId = Long.valueOf(req.getParameter("userId"));
        UserService userService = UserService.newInstance();
        User user = userService.getUserById(userId);

        if (newPassword.equals(confirmNewPassword)
                && userService.updateProfile(user, firstName, lastName, phone, address)
                && userService.updatePassword(user, newPassword)) {

            resp.sendRedirect("/update-user");
        } else {
            resp.sendRedirect("/update-user");
        }
    }
}