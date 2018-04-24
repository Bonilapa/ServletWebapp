package model.dao.interfaces;


import model.pojo.Order;
import model.pojo.Tour;

import java.util.List;

public interface OrderDAO extends DAO<Order, Integer> {

    Tour getByTourId(Integer userId, Integer tourId);

    List<Tour> getByUserId(Integer userId);

    Boolean insertOrder(Order order, Integer userId);
}