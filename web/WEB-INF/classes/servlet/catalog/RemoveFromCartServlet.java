package servlet.catalog;

import entity.order.Order;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productToRemoveId = Long.valueOf(req.getParameter("productToRemoveId"));
        int productToRemoveQty = Integer.valueOf(req.getParameter("productToRemoveQty"));
        Order order = (Order) req.getSession().getAttribute("order");
        CartService.newInstance().removeProductFromCart(order, productToRemoveId, productToRemoveQty);
        resp.sendRedirect("/cart");
    }
}
