package service.impl;

import model.dao.impl.OrderDAOImpl;
import model.dao.impl.TourDAOImpl;
import model.dao.interfaces.OrderDAO;
import model.pojo.Order;
import model.pojo.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.interfaces.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    private OrderDAOImpl orderDAO = new OrderDAOImpl();

    @Override
    public List<Tour> getAllTours(Integer userId){

        LOGGER.debug("OrderService. getAllTours for user: "+ userId);
        return orderDAO.getByUserId(userId);
    }

    @Override
    public Tour getTourById(Integer tourId, Integer userId) {

        LOGGER.debug("OrderService. getTourById: " + tourId + "for user: " + userId);
        return orderDAO.getByTourId(userId, tourId);
    }

    @Override
    public Boolean addNewOrder(Order entity, Integer userId) {

        LOGGER.debug("TourService. addNewTour.");

        Tour tour = orderDAO.getByTourId(userId, entity.getTour().getId());

        if(tour == null) {

            LOGGER.debug("Set new tour: " + entity.getTour().getId() + " for user:" + userId);
            return orderDAO.insertOrder(entity, userId);

        }else{

            LOGGER.debug("Tour: " + entity.getTour().getId() + " already exists.");
            return false;
        }

    }

}
