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
        name = "order", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    private static OrderService orderService = new OrderServiceImpl();
    private static final Logger LOGGER = LogManager.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("OrderServlet doGet");

        if(req.getSession().getAttribute("userName") != null) {

            req.setAttribute("userName", req.getSession().getAttribute("userName"));
            Integer userId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
            req.setAttribute("list", orderService.getAllTours(userId));
        }

        try {

            getServletContext().getRequestDispatcher("/orders.jsp").forward(req, resp);

        } catch (ServletException e) {

            LOGGER.error("ServletException when OrderServlet forwards to /orders.jsp.");
            e.printStackTrace();

        } catch (IOException e) {

            LOGGER.error("IOEException when OrderServlet forwards to /orders.jsp.");
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        LOGGER.debug("OrderServlet doPost.");

        String getDescription = req.getParameter("getDescription");
        String dislogin = req.getParameter("dislogin");

        if(dislogin!=null){

            req.getSession().removeAttribute("userName");

            try {

                resp.sendRedirect(req.getContextPath()+"/login");

            } catch (IOException e) {

                LOGGER.error("IOEException when OrderServlet redirects to /login.jsp.");
                e.printStackTrace();
            }

        }else {

            if (getDescription != null) {

                boolean validationFailed = false;
                Integer id = -1001;

                try {

                    id = Integer.parseInt(getDescription);

                } catch (NumberFormatException ex) {

                    LOGGER.error("NumberFormatException when OrderServlet doPost tourId = " + getDescription);
                    validationFailed = true;
                }

                if (id < -1000)
                    validationFailed = true;

                if (validationFailed) {

                    try {

                        resp.sendRedirect(req.getContextPath() + "/error");

                    } catch (IOException e) {

                        LOGGER.error("Unknown tourId. IOEException when OrderServlet redirects to /error.");
                        e.printStackTrace();
                    }

                    return;
                }

                Tour tour = null;
                Integer userId = Integer.parseInt(req.getSession().getAttribute("userId").toString());

                if (id > 0) {

                    LOGGER.debug("OrderServlet doPost OrderService");
                    tour = orderService.getTourById(id, userId);
                }

                if (tour != null) {

                    req.setAttribute("name", tour.getName());
                    req.setAttribute("price", tour.getPrice());
                    req.setAttribute("date", tour.getDate());
                    req.setAttribute("description", tour.getDescription());

                } else {

                    req.setAttribute("name", "");
                    req.setAttribute("age", "");
                    req.setAttribute("groupId", "");
                    req.setAttribute("errorMessage", "No such tour ordered.");

                }

                try {

                    getServletContext().getRequestDispatcher("/order.jsp").forward(req, resp);

                } catch (ServletException e) {

                    LOGGER.error("ServletException when LoginServlet forwards to /order.jsp.");
                    e.printStackTrace();

                } catch (IOException e) {

                    LOGGER.error("IOException when LoginServlet forwards to /order.jsp.");
                    e.printStackTrace();
                }

            } else {

                try {

                    resp.sendRedirect(req.getContextPath() + "/order");

                } catch (IOException e) {

                    LOGGER.error("IOException when LoginServlet redirects to /order.");
                    e.printStackTrace();
                }

            }

        }

    }

}