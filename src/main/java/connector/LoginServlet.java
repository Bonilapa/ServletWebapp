package connector;

import model.pojo.Tour;
import service.impl.LoginServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    //private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
    private static UserServiceImpl userService = new UserServiceImpl();
    private static LoginServiceImpl loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().print("lalala");
        //LOGGER.debug("LoginServlet doGet");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        //resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        String userName = loginService.auth(req.getParameter("login"), req.getParameter("password"));
        if (userName != null) {
            req.getSession().setAttribute("userName", userName);
            resp.sendRedirect("/tour");
        } else {
            req.setAttribute("errorMessage", new String("Incorrect login or password."));
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }
    }
}
