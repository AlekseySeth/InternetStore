package servlet;

import entity.user.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author a.shestovsky
 */

@WebServlet("/my-account/admin")
public class AdminServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String email = req.getParameter("email");

        User userByEmail = new UserService().getUserByEmail(email);

        writer.write("<p>" + userByEmail.getId() + ". " + userByEmail.getFirstName() + " " + userByEmail.getLastName() + " "
                        + userByEmail.getEmail() + " " + userByEmail.getAddress() + " " + userByEmail.getPhone() + " "
                        + userByEmail.getRegistrationDate() + " " + userByEmail.getRole().name().toLowerCase() + "</p>");


    }
}
