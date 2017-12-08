package servlet.account;

import entity.order.Order;
import entity.product.Product;
import entity.user.Role;
import entity.user.User;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/log-out",  name = "LogOut")
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/my-account");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logOut = req.getParameter("logOut");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (logOut == null) {
            resp.sendRedirect(req.getHeader("Referer"));
        } else if (logOut.equals("true") && user.getRole().equals(Role.CUSTOMER)) {
            if (session.getAttribute("isPlaced") != null && session.getAttribute("isPlaced").equals(true)) {
                session.setAttribute("isPlaced", false);
            } else {
                Order order = (Order) session.getAttribute("order");
                Map<Product, Integer> products = order.getProducts();
                for (Map.Entry entry : products.entrySet()) {
                    CartService.newInstance().removeProductFromCart(order, (Product) entry.getKey(), (int) entry.getValue());
                }
            }
        }
        session.invalidate();
        resp.sendRedirect("/");
    }
}