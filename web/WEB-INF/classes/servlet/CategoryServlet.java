package servlet;

import entity.product.Category;
import service.CatalogService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.ServletUtil.getPath;

/**
 * @author a.shestovsky
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> allCategories = new CatalogService().getAllCategories();


        RequestDispatcher requestDispatcher = req.getServletContext()
                .getRequestDispatcher(getPath("category"));
        requestDispatcher.forward(req, resp);
    }
}
