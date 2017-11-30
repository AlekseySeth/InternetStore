package servlet.account;

import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/download-order", name = "DownloadOrder")
public class DownloadOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        String fileName = "Order_" + orderId + ".txt";
        resp.setHeader("Content-disposition","attachment; filename=" + fileName);

        OrderService.newInstance().generateInvoice(orderId, fileName);

        ServletOutputStream outputStream = resp.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(fileName);

        byte[] buffer = new byte[4096];
        int length;

        while ((length = fileInputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }
        fileInputStream.close();
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        OrderService.newInstance().getOrderById(orderId);
        resp.sendRedirect("/my-account");
    }
}