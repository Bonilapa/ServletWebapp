package connector;

import model.pojo.Order;
import model.pojo.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.impl.OrderServiceImpl;
import service.impl.TourServiceImpl;
import service.impl.UserServiceImpl;
import service.interfaces.OrderService;
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
    private static final Logger LOGGER = LogManager.getLogger(TourServlet.class);
    private static OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        LOGGER.debug("TourServlet doGet");

         if(req.getSession().getAttribute("userName") != null) {

             req.setAttribute("userName", req.getSession().getAttribute("userName"));
         }

         LOGGER.debug("TourServlet gets all tours.");
        req.setAttribute("list", tourService.getAllTours());

        try {

            getServletContext().getRequestDispatcher("/tours.jsp").forward(req, resp);

        } catch (ServletException e) {

            LOGGER.error("ServletException when TourServlet forwards to /tours.jsp.",e);

        } catch (IOException e) {

            LOGGER.error("IOException when TourServlet forwards to /tours.jsp.",e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("TourServlet doPost.");

        String getDescription = req.getParameter("getDescription");
        String act = req.getParameter("act");
        String tId = req.getParameter("tId");

        if(act != null && tId != null){

            Integer userId = -1000;
            Integer tourId = -1000;

            try{

                userId = Integer.parseInt(act);

            }catch(NumberFormatException e){

                LOGGER.error("NumberFormatException when TourServlet doPost. userId = " + userId, e);

            }

            try {

                tourId = Integer.parseInt(tId);

            }catch(NumberFormatException e){

                LOGGER.error("NumberFormatException when TourServlet doPost tourId = " + tourId, e);
            }

            if(userId > 0 && tourId > 0) {

                LOGGER.debug("OrderServlet adds new tour into user orders.");
                orderService.addNewOrder(new Order(tourService.getTourById(tourId)), userId);

                try {

                    resp.sendRedirect(req.getContextPath()+"/order");

                } catch (IOException e) {

                    LOGGER.error("IOException when OrderServlet redirects to /order.", e);
                }

                return;
            }

        }

        String dislogin = req.getParameter("dislogin");

        if(dislogin != null){

            req.getSession().removeAttribute("userName");

            try {

                resp.sendRedirect(req.getContextPath()+"/login");

            } catch (IOException e) {

                LOGGER.error("IOException when OrderServlet redirects to /login.", e);
            }

        }else {

            if (getDescription != null) {

                boolean validationFailed = false;
                Integer id = -1001;

                try {

                    id = Integer.parseInt(getDescription);

                } catch (NumberFormatException ex) {

                    LOGGER.error("NumberFormatException when TourServlet doPost tourId = " + id);
                    validationFailed = true;
                }

                if (id < -1000) {
                    validationFailed = true;
                }

                if (validationFailed) {

                    try {

                        resp.sendRedirect(req.getContextPath() + "/error");

                    } catch (IOException e) {

                        LOGGER.error("IOException when OrderServlet redirects to /login.", e);
                    }

                    return;
                }

                Tour tour = null;

                if (id > 0) {

                    LOGGER.debug("TourServlet gets single tour. tourId = " + id);
                    tour = tourService.getTourById(id);
                }

                if (tour != null) {

                    req.setAttribute("tourId", tour.getId());
                    req.setAttribute("name", tour.getName());
                    req.setAttribute("price", tour.getPrice());
                    req.setAttribute("date", tour.getDate());
                    req.setAttribute("description", tour.getDescription());

                } else {

                    req.setAttribute("name", "");
                    req.setAttribute("age", "");
                    req.setAttribute("groupId", "");
                    req.setAttribute("errorMessage", "No such tour left.");
                }

                try {

                    getServletContext().getRequestDispatcher("/tour.jsp").forward(req, resp);

                } catch (ServletException e) {

                    LOGGER.error("ServletException when TourServlet forwards to /tour.jsp.", e);

                } catch (IOException e) {

                    LOGGER.error("IOException when TourServlet forwards to /tour.jsp.", e);
                }

            } else {

                try {

                    resp.sendRedirect(req.getContextPath() + "/tour");

                } catch (IOException e) {

                    LOGGER.error("IOException when TourServlet redirects to /tour.", e);
                }

            }

        }

    }

}