package connector;

import model.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.impl.LoginServiceImpl;

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
    private static LoginServiceImpl loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("LoginServlet doGet.");

        try {

            req.getRequestDispatcher("/login.jsp").forward(req, resp);

        } catch (ServletException e) {

            LOGGER.error("ServletException when LoginServlet forwards to /login.jsp.");
            e.printStackTrace();

        } catch (IOException e) {

            LOGGER.error("IOEException when LoginServlet forwards to /login.jsp.");
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("LoginServlet doGet.");

        User user = loginService.auth(req.getParameter("login"), req.getParameter("password"));

        if (user != null && user.getLogin() != null) {

            req.getSession().setAttribute("userName", user.getLogin());
            req.getSession().setAttribute("userId", user.getId());

            try {

                resp.sendRedirect("/tour");

            } catch (IOException e) {

                LOGGER.error("IOException when LoginServlet redirects to /tour.");
                e.printStackTrace();
            }

        } else {

            req.setAttribute("errorMessage", new String("Incorrect login or password."));

            try {

                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

            } catch (ServletException e) {

                LOGGER.error("ServletException when LoginServlet forwards to /login.jsp.");
                e.printStackTrace();

            } catch (IOException e) {

                LOGGER.error("IOEException when LoginServlet forwards to /login.jsp.");
                e.printStackTrace();
            }

        }

    }

}