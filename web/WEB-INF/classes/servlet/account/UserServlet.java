package servlet.account;

import dto.OrderDto;
import entity.user.User;
import service.OrderService;
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
@WebServlet(urlPatterns = "/user", name = "UserInfo")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String email = req.getParameter("userEmail");
        User user;

        if (userId == null) {
            if (email.isEmpty()) {
                resp.sendRedirect("/my-account");
            } else {
                user = UserService.newInstance().getUserByEmail(email);
                req.setAttribute("foundUser", user);
                List<OrderDto> orders = OrderService.newInstance().getOrdersByUser(user);
                req.setAttribute("orders", orders);
                req.getServletContext().getRequestDispatcher(getPath("user")).forward(req, resp);
            }
        } else if (email == null) {
            if (userId.isEmpty()) {
                resp.sendRedirect("/my-account");
            } else {
                Long id = Long.valueOf(userId);
                user = UserService.newInstance().getUserById(id);
                req.setAttribute("foundUser", user);
                List<OrderDto> orders = OrderService.newInstance().getOrdersByUser(user);
                req.setAttribute("orders", orders);
                req.getServletContext().getRequestDispatcher(getPath("user")).forward(req, resp);
            }
        }
    }
}