package connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import service.impl.UserServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "hello", urlPatterns = {"/"})
public class HelloServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("HelloServlet doGet.");

        String dislogin = req.getParameter("dislogin");

        if(dislogin!=null){

            req.getSession().removeAttribute("userName");

            try {

                resp.sendRedirect(req.getContextPath()+"/login");

            } catch (IOException e) {

                LOGGER.error("IOEXception when HelleServlet redirects to /login.", e);

            }
        }else {
            try {

                req.getRequestDispatcher("/index.jsp").forward(req, resp);

            } catch (IOException e) {

                LOGGER.error("IOException when HelloServlet forwards to index.jsp dispatcher.", e);

            } catch (ServletException e) {

                LOGGER.error("ServletException when HelloServlet forwards to index.jsp dispatcher.", e);

            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("HelloServlet doPost.");

        String dislogin = req.getParameter("dislogin");

        if(dislogin!=null){

            req.getSession().removeAttribute("userName");

            try {

                resp.sendRedirect(req.getContextPath()+"/login");

            } catch (IOException e) {

                LOGGER.error("IOException when HelloServlet redirects to /login.", e);

            }

        }else {

            try {

                resp.sendRedirect(req.getContextPath() + "/tour");

            } catch (IOException e) {

                LOGGER.error("IOException when HelloServlet redirects to /tour.", e);

            }

        }

    }

}