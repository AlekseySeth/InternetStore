package servlet.catalog;

import entity.order.Order;
import service.CartService;
import service.CatalogService;

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

@WebServlet(urlPatterns = "/product", name = "Product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", CatalogService.newInstance().getParentCategories());
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("product", CatalogService.newInstance().getProductById(id));

        req.getServletContext()
            .getRequestDispatcher(getPath("product"))
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int qty = Integer.valueOf(req.getParameter("qty"));
        Long id = Long.valueOf(req.getParameter("id"));
        Order order = (Order) req.getSession().getAttribute("order");
        CartService.newInstance().addProductToCart(order, id, qty);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}