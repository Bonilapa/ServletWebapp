package connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.interfaces.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "base", urlPatterns = {"/base"})
public class BaseServlet extends HttpServlet {
    private static UserService userService = new UserServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().print("lalala");
        //LOGGER.debug("BaseServlet doGet");
        //req.setAttribute("list", userService.getAllUsers());
        //getServletContext().getRequestDispatcher("/listStudents.jsp").forward(req, resp);
        req.getRequestDispatcher("/base.jsp").forward(req, resp);

        //req.setAttribute("list", userService.getAllUsers());
        //resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", userService.getAllUsers());
        getServletContext().getRequestDispatcher("/base.jsp").forward(req, resp);
    }
}