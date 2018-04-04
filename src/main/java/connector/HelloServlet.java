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
        private static UserServiceImpl userService = new UserServiceImpl() ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().print("lalala");
        LOGGER.debug("HelloServlet doGet");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        //resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("list", userService.getAllUsers());
         req.getSession();
        resp.sendRedirect(req.getContextPath() + "/tour");
    }
}