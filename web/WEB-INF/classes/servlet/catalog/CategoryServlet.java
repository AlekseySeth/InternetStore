package servlet.catalog;

import entity.product.Category;
import service.CatalogService;

import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = "/category", name = "Category")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", CatalogService.newInstance().getParentCategories());
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("products", CatalogService.newInstance().getProductsByCategory(id));
        req.setAttribute("currentCategory", CatalogService.newInstance().getCategoryById(id));

        req.getServletContext()
            .getRequestDispatcher(getPath("category"))
            .forward(req, resp);
    }
}