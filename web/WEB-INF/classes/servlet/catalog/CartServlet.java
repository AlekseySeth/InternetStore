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
@WebServlet(urlPatterns = "/cart", name = "Cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = (Order) req.getSession().getAttribute("order");
        CartService cartService = CartService.newInstance();
        req.setAttribute("deliveries", cartService.getAllDeliveries());
        req.setAttribute("subtotalPrice", cartService.calculateSubtotalPrice(order));
        order.setTotalPrice(cartService.calculateTotalPrice(order));
        req.getServletContext().getRequestDispatcher(getPath("cart")).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = (Order) req.getSession().getAttribute("order");
        Long deliveryId = Long.valueOf(req.getParameter("delivery"));
        CartService.newInstance().setOrderDelivery(order, deliveryId);
        req.getSession().setAttribute("isPlaced", true);
        resp.sendRedirect("/cart");
    }
}