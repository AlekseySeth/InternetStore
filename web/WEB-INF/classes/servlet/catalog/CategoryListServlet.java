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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/category-list", name = "CategoryList")
public class CategoryListServlet extends HttpServlet {

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

        req.getServletContext()
                .getRequestDispatcher(getPath("category-list"))
                .forward(req, resp);
    }
}