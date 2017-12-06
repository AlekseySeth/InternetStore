package servlet.catalog;

import entity.product.Category;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/category", name = "Category")
public class CategoryServlet extends HttpServlet {

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
        req.setAttribute("productsCatalog", CatalogService.newInstance().getProductsByCategory(id));
        Category currentCategory = CatalogService.newInstance().getCategoryById(id);
        if (currentCategory.getCategory() != null) {
            req.setAttribute("parentCategory", currentCategory.getCategory());
        }
        req.setAttribute("currentCategory", currentCategory);

        req.getServletContext()
            .getRequestDispatcher(getPath("category"))
            .forward(req, resp);
    }
}