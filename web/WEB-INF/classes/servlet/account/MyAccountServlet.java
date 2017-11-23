package servlet.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a.shestovsky
 */
@WebServlet("/my-account")
public class MyAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        проверяет сессию на наличие пользователя. если пользователя нет, то редирект на sign-in
//        если пользователь есть, то на страницу его аккаунта
    }
}
