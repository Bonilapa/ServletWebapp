package connector;

import model.pojo.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.impl.TourServiceImpl;
import service.impl.UserServiceImpl;
import service.interfaces.TourService;
import service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "tour", urlPatterns = {"/tour"})
public class TourServlet extends HttpServlet {
    private static TourService tourService = new TourServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().print("lalala");
        //LOGGER.debug("BaseServlet doGet");
        //req.setAttribute("list", userService.getAllUsers());
        //getServletContext().getRequestDispatcher("/listStudents.jsp").forward(req, resp);
        //req.getRequestDispatcher("/base.jsp").forward(req, resp);

        //req.setAttribute("list", userService.getAllUsers());
        //resp.sendRedirect("/");
         req.setAttribute("list", tourService.getAllTours());
         getServletContext().getRequestDispatcher("/tours.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getDescription = req.getParameter("getDescription");
        //System.out.println(getDescription);
        if(getDescription != null){
            //.out.println("2222222");
            boolean validationFailed = false;
            Integer id = -1001;
            try {
                id = Integer.parseInt(getDescription);
            } catch (NumberFormatException ex){
                //LOGGER.debug("StudentServlet doGet idToEdit = " + getDescription);
                validationFailed = true;
            }

            if (id < -1000)
                validationFailed = true;

            if(validationFailed){
                resp.sendRedirect(req.getContextPath() + "/error");
                return;
            }

            Tour tour = null;
            if(id>0){
                tour = tourService.getTourById(id);
            }

            //System.out.println("333333333");
            if (tour != null) {
                req.setAttribute("name", tour.getName());
                req.setAttribute("price", tour.getPrice());
                req.setAttribute("date", tour.getDate());
                req.setAttribute("description", tour.getDescription());
            } else {

                req.setAttribute("name", "");
                req.setAttribute("age", "");
                req.setAttribute("groupId", "");
            }

            getServletContext().getRequestDispatcher("/tour.jsp").forward(req, resp);
            //System.out.println("5555555");
        } else {
            resp.sendRedirect(req.getContextPath() + "/tour");
        }
    }
}