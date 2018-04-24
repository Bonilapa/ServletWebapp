package service.interfaces;

import model.pojo.Order;
import model.pojo.Tour;

import java.util.List;

public interface OrderService {

    List<Tour> getAllTours(Integer userId);

    Tour getTourById(Integer id, Integer userId);

    Boolean addNewOrder(Order entity, Integer userId);
}
