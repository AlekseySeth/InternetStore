package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author a.shestovsky
 */
@WebServlet(urlPatterns = "/language", name = "Language")
public class LanguageSelectorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        HttpSession session = req.getSession();
        session.setAttribute("language", language);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}