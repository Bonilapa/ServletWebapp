package connector;

import model.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.impl.LoginServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private static UserServiceImpl userService = new UserServiceImpl();
    private static LoginServiceImpl loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"), req.getParameter("password"));
        Integer state = userService.addUser(user);
        if (state == 0) {
            req.getSession().setAttribute("userName", user.getLogin());
            resp.sendRedirect(req.getContextPath() + "/tour");
        }
    }
}
