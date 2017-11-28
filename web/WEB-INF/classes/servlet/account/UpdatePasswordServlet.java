package servlet.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/update-password")
public class UpdatePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter("newPassword");
        String confirmNewPassword = req.getParameter("confirmNewPassword");

        if (newPassword.equals(confirmNewPassword)) {

        } else {
            req.setAttribute("message", "failed");
            resp.sendRedirect("/update-password");
        }
    }
}