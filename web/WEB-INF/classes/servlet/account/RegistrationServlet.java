package servlet.account;

import entity.user.Role;
import entity.user.User;
import service.AuthenticationService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/registration", name = "Registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String originalPassword = req.getParameter("password");
        String repeatedPassword = req.getParameter("repPassword");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Date registrationDate = new Date(System.currentTimeMillis());
        Role role = Role.CUSTOMER;
        User newUser = null;
        HttpSession session = req.getSession();

        if (originalPassword.equals(repeatedPassword)) {
            String encryptedPassword = AuthenticationService.newInstance().encryptPassword(email, originalPassword);
            newUser = UserService.newInstance().createNewUser(
                    new User(firstName, lastName, email, encryptedPassword, phone, address, registrationDate, role));
        } else {
//            сообщение об ошибке несовпадение паролей
        }

        if (newUser.getId() == null) {
//          сообщение об ошибке при регистрации пользователя
            System.out.println("Registration error");
        } else {
            session.setAttribute("user", newUser);
            session.setAttribute("order", AuthenticationService.newInstance().createInitialOrder(newUser));
            resp.sendRedirect("/my-account");
        }
    }
}