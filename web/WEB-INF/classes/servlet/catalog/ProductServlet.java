package servlet.catalog;

import dao.ProductDao;
import entity.product.Product;
import service.CatalogService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */

@WebServlet(urlPatterns = "/product", name = "Product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(getInitParameter("id"));
        req.setAttribute("product", CatalogService.newInstance().getProductById(id));

        RequestDispatcher requestDispatcher = req.getServletContext()
                .getRequestDispatcher(getPath("product"));
        requestDispatcher.forward(req, resp);
    }
}