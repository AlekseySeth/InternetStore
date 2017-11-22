package servlet.catalog;

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
@WebServlet("/category-list")
public class CategoryListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", CatalogService.newInstance().getParentCategories());

        RequestDispatcher requestDispatcher = req.getServletContext()
                .getRequestDispatcher(getPath("category-list"));
        requestDispatcher.forward(req, resp);
    }
}