package servlet.catalog;

import entity.order.Order;
import service.CartService;

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
@WebServlet(urlPatterns = "/order-placed", name = "OrderPlaced")
public class OrderPlacedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getServletContext()
                .getRequestDispatcher(getPath("order-placed"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = (Order) req.getSession().getAttribute("order");
        CartService.newInstance().placeOrder(order);
        req.getSession().setAttribute("isPlaced", true);
        resp.sendRedirect("/order-placed");
    }
}