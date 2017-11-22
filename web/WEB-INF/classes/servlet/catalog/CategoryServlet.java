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
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
// переписать: вынести логику создания categoryById в сервис. getProductsByCategory принимает сразу id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(getInitParameter("id"));
        CatalogService catalogService = CatalogService.newInstance();
        Category categoryById = catalogService.getCategoryById(id);

        req.setAttribute("products", catalogService.getProductsByCategory(categoryById));

        RequestDispatcher requestDispatcher = req.getServletContext()
                .getRequestDispatcher(getPath("category"));
        requestDispatcher.forward(req, resp);

    }
}