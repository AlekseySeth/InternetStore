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
        req.setAttribute("deliveries", CartService.newInstance().getAllDeliveries());
        req.getServletContext().getRequestDispatcher(getPath("cart")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int qty = Integer.valueOf(req.getParameter("qty"));
        Long productId = Long.valueOf(req.getParameter("productId"));
        Order order = (Order) req.getSession().getAttribute("order");
        CartService.newInstance().addProductToCart(order, productId, qty);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}