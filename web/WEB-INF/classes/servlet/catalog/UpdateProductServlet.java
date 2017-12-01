package servlet.catalog;

import entity.product.Product;
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
@WebServlet(urlPatterns = "/update-product", name = "UpdateProduct")
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        Product product = CatalogService.newInstance().getProductById(productId);
        req.setAttribute("product", product);
        req.getServletContext().getRequestDispatcher(getPath("update-product")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        int qtyInStock = Integer.valueOf(req.getParameter("qtyInStock"));
        Long categoryId = Long.valueOf(req.getParameter("categoryId"));
        String imageURL = req.getParameter("imageURL");

        CatalogService catalogService = CatalogService.newInstance();
        Product product = catalogService.getProductById(productId);

        if (catalogService.updateProduct(product, name, description, price, qtyInStock, categoryId, imageURL)) {
            resp.sendRedirect("/update-product");
        } else {
            resp.sendRedirect("/update-product");
        }
    }
}