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
    private static UserServiceImpl userService = new UserServiceImpl() ;
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
        //boolean validationFailed = false;
        //resp.sendRedirect(req.getContextPath() + "/tour");
        Integer where = 0;
        String getDescription = req.getParameter("getDescription");
        //System.out.println(getDescription);

        System.out.println(where);
        if(getDescription != null){
            try {
                where = Integer.parseInt(getDescription);
            } catch (NumberFormatException ex){
                //LOGGER.debug("StudentServlet doGet idToEdit = " + getDescription);
                //validationFailed = true;
            }
            System.out.println(where);
            if(where == 1) {
                getServletContext().getRequestDispatcher("/tours.jsp").forward(req, resp);
                return;
            }else if(where == 2) {
                resp.sendRedirect(req.getContextPath() + "/register");
            }
            //System.out.println("333333333");

            //System.out.println("5555555");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//
//        //LOGGER.debug("Login servlet");
//        if(loginService.auth(login, password) == null) {
//            req.getSession().setAttribute("login", login);
//
//        } else {
//            resp.sendRedirect(req.getContextPath() + "/error");
//        }
    }
}