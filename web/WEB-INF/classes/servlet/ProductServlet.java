package servlet;

import dao.ProductDao;
import entity.product.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * @author a.shestovsky
 */

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String result = ProductDao.newInstance().getAll()
                .stream()
                .map(this::wrapInParagraph)
                .collect(Collectors.joining());

        writer.write(result);
    }

    private String wrapInParagraph(Product product) {
        return "<p>" + product.toString() + "</p>";
    }


}
