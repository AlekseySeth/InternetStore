package servlet.catalog;

import entity.product.Category;
import entity.product.Product;
import service.CatalogService;

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
@WebServlet(urlPatterns = "/update-product", name = "UpdateProduct")
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");

        Map<Category, List<Category>> categoryTree = new HashMap<>();
        List<Category> parentCategories = CatalogService.newInstance().getParentCategories();
        for (Category parent : parentCategories) {
            List<Category> child = CatalogService.newInstance().getCategoriesByParentId(parent.getId());
            categoryTree.put(parent, child);
        }

        req.setAttribute("categoryTree", categoryTree);
        if (productIdString == null || productIdString.isEmpty()) {
            resp.sendRedirect("/my-account");
        } else {
            Long productId = Long.valueOf(productIdString);
            Product product = CatalogService.newInstance().getProductById(productId);
            req.setAttribute("product", product);
            req.getServletContext().getRequestDispatcher(getPath("update-product")).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        int qtyInStock = Integer.valueOf(req.getParameter("qtyInStock"));
        String imageURL = req.getParameter("imageURL");
        Long categoryId = Long.valueOf(req.getParameter("category"));

        CatalogService catalogService = CatalogService.newInstance();
        Product product = catalogService.getProductById(productId);

        if (catalogService.updateProduct(product, name, description, price, qtyInStock, categoryId, imageURL)) {
            resp.sendRedirect("/update-product");
        } else {
            resp.sendRedirect("/update-product");
        }
    }
}