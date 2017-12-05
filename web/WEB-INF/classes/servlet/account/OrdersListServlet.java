package servlet.account;

import dto.OrderFullDto;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.ServletUtil.getPath;

@WebServlet(urlPatterns = "/orders-list", name = "OrdersList")
public class OrdersListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderFullDto> orders = OrderService.newInstance().getAllOrders();
        req.setAttribute("orders", orders);
        req.getServletContext().getRequestDispatcher(getPath("orders-list")).forward(req, resp);
    }
}
