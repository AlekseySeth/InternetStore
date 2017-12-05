package servlet.catalog;

import entity.order.Order;
import entity.product.Category;
import entity.user.Role;
import entity.user.User;
import service.CartService;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */

@WebServlet(urlPatterns = "/product", name = "Product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> parentCategories = CatalogService.newInstance().getParentCategories();
        List<Category> childCategories = new ArrayList<>();
        req.setAttribute("categories", parentCategories);

        for (Category parent : parentCategories) {
            List<Category> subCategories = CatalogService.newInstance().getCategoriesByParentId(parent.getId());
            childCategories.addAll(subCategories);
        }
        req.setAttribute("childCategories", childCategories);
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("productPage", CatalogService.newInstance().getProductById(id));

        req.getServletContext()
            .getRequestDispatcher(getPath("product"))
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.MARKETER)) {
            resp.sendRedirect("/login");
        } else {
            int qty = Integer.valueOf(req.getParameter("qty"));
            Long id = Long.valueOf(req.getParameter("id"));
            Order order = (Order) session.getAttribute("order");
            CartService.newInstance().addProductToCart(order, id, qty);
            session.setAttribute("isPlaced", false);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}