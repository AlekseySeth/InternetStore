package servlet.account;

import entity.user.Role;
import entity.user.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

/**
 * @author a.shestovsky
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        BufferedReader reader = req.getReader();

        String firstName = reader.readLine();
        String lastName = reader.readLine();
        String email = reader.readLine();
        String password = reader.readLine();
        String phone = reader.readLine();
        String address = reader.readLine();
        Date registrationDate = new Date(System.currentTimeMillis());
        Role role = Role.CUSTOMER;

        User newUser = UserService.newInstance().createNewUser(
                new User(firstName, lastName, email, password, phone, address, registrationDate, role));


        if (newUser.getId() == null) {

        }


    }
}