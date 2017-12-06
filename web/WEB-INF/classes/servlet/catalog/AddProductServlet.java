package servlet.catalog;

import entity.product.Category;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/add-product", name = "AddProduct")
public class AddProductServlet extends HttpServlet {

    private static final String IMAGE_PATH = "resources" + File.separator + "images";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Category, List<Category>> categoryTree = new HashMap<>();
        List<Category> parentCategories = CatalogService.newInstance().getParentCategories();
        for (Category parent : parentCategories) {
            List<Category> child = CatalogService.newInstance().getCategoriesByParentId(parent.getId());
            categoryTree.put(parent, child);
        }

        req.setAttribute("categoryTree", categoryTree);
        req.getServletContext().getRequestDispatcher(getPath("add-product")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        int qtyInStock = Integer.valueOf(req.getParameter("qtyInStock"));
        String imageURL = req.getParameter("imageURL");
        Long categoryId = Long.valueOf(req.getParameter("category"));

        CatalogService.newInstance().addNewProduct(name, description, price, qtyInStock, categoryId, imageURL);

        resp.sendRedirect("/products-list");
    }
}