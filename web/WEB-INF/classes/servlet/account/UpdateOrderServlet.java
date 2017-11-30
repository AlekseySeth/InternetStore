package servlet.account;

import entity.order.Order;
import entity.order.Status;
import service.OrderService;

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
@WebServlet(urlPatterns = "/update-order", name = "UpdateOrder")
public class UpdateOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        Order order = OrderService.newInstance().getOrderById(orderId);
        req.setAttribute("order", order);
        req.getServletContext().getRequestDispatcher(getPath("update-order")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("orderID"));
        Order order = OrderService.newInstance().getOrderById(orderId);
        String status = req.getParameter("status");

        if (OrderService.newInstance().updateOrder(order,  status)) {
            resp.getWriter().write("<p id=\"message\">Заказ изменен</p>");
            resp.sendRedirect("/update-order?orderId="+ order.getId());
        } else {
            resp.sendRedirect("/update-order?orderId="+ order.getId());
        }
    }
}