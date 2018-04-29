package connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "error", urlPatterns = {"/error"})
public class ErrorServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ErrorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("ErrorServlet doGet");
        try {

            req.getRequestDispatcher("/error.jsp").forward(req, resp);

        } catch (ServletException e) {

            LOGGER.error("ServletException when ErrorServlet forwards to /error.jsp", e);

        } catch (IOException e) {

            LOGGER.error("IOEException when ErrorServlet forwards to /error.jsp", e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("ErrorServlet doPost");
        try {

            super.doPost(req, resp);

        } catch (ServletException e) {

            LOGGER.error("ServletException when ErrorServlet super.forwards to /error.jsp", e);

        } catch (IOException e) {

            LOGGER.error("IOEException when ErrorServlet super.forwards to /error.jsp", e);
        }

    }

}