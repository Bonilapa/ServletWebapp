package connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private static UserServiceImpl userService = new UserServiceImpl() ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().print("lalala");
        LOGGER.debug("LoginServlet doGet");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        //resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        //LOGGER.debug("Login servlet");

        if(userService.auth(login, password) != null) {
            req.getSession().setAttribute("userLogin", login);
            resp.sendRedirect(req.getContextPath() + "/listStudents");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}