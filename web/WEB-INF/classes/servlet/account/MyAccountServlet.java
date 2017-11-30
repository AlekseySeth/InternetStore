package servlet.account;

import dto.OrderDto;
import entity.user.User;
import service.OrderService;

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
@WebServlet(urlPatterns = "/my-account", name = "MyAccount")
public class MyAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<OrderDto> orders = OrderService.newInstance().getOrdersByUser(user);
        req.setAttribute("orders", orders);
        req.getServletContext()
                .getRequestDispatcher(getPath("my-account"))
                .forward(req, resp);
    }
}