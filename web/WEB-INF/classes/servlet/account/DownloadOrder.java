package servlet.account;

import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/download-order", name = "DownloadOrder")
public class DownloadOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        OrderService.newInstance().downloadOrderFile(orderId);
        resp.sendRedirect("/my-account");
    }
}