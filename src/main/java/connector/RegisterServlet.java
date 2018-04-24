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

    private static final Logger LOGGER = LogManager.getLogger(RegisterServlet.class);
    private static UserServiceImpl userService = new UserServiceImpl();
    private static LoginServiceImpl loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("RegisterServlet doGet.");

        try {

            req.getRequestDispatcher("/register.jsp").forward(req, resp);

        } catch (ServletException e) {

            LOGGER.error("ServletException when RegisterServlet forwards to /register.jsp.");
            e.printStackTrace();

        } catch (IOException e) {

            LOGGER.error("IOException when RegisterServlet forwards to /register.jsp.");
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("RegisterServlet doPost.");

        User user = new User(req.getParameter("login"), req.getParameter("password"));

        LOGGER.debug("RegisterServlet adds new user");
        Integer state = userService.addUser(user);

        if (state == null) {

            req.setAttribute("errorMessage", "Such user already exists.");

            try {

                getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);

            } catch (ServletException e) {

                LOGGER.error("ServletException when RegisterServlet forwards to /register.jsp.");
                e.printStackTrace();

            } catch (IOException e) {

                LOGGER.error("IOException when RegisterServlet forwards to /register.jsp.");
                e.printStackTrace();
            }

        } else {

            switch (state) {

                case 0: {

                    req.getSession().setAttribute("userName", user.getLogin());
                    User user1 = loginService.auth(user.getLogin(), user.getPassword());
                    req.getSession().setAttribute("userId", user1.getId());

                    try {

                        resp.sendRedirect(req.getContextPath() + "/tour");

                    } catch (IOException e) {

                        LOGGER.error("IOException when RegisterServlet redirects to /tour.");
                        e.printStackTrace();
                    }

                    break;
                }

                case 1: {

                    req.setAttribute("errorMessage", new String("Empty Password."));

                    try {

                        getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);

                    } catch (ServletException e) {

                        LOGGER.error("ServletException when RegisterServlet forwards to /register.jsp.");
                        e.printStackTrace();

                    } catch (IOException e) {

                        LOGGER.error("IOException when RegisterServlet forwards to /register.jsp.");
                        e.printStackTrace();
                    }

                    break;
                }

                case -1: {

                    req.setAttribute("errorMessage", new String("Empty Login."));

                    try {

                        getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);

                    } catch (ServletException e) {

                        LOGGER.error("ServletException when RegisterServlet forwards to /register.jsp.");
                        e.printStackTrace();

                    } catch (IOException e) {

                        LOGGER.error("IOException when RegisterServlet forwards to /register.jsp.");
                        e.printStackTrace();
                    }

                    break;
                }

            }

        }

    }

}
