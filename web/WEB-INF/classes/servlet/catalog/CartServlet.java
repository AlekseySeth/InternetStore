package servlet.catalog;

import entity.order.Order;
import entity.user.User;
import service.AuthenticationService;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/cart", name = "Cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (session.getAttribute("isPlaced").equals(true)) {
            session.setAttribute("order", AuthenticationService.newInstance().createInitialOrder(user));
            session.setAttribute("isPlaced", false);
        }
        Order order = (Order) session.getAttribute("order");
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
        resp.sendRedirect("/cart");
    }
}