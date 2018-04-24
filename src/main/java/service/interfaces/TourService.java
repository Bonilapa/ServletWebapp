package service.interfaces;

import model.pojo.Tour;
import java.util.List;

public interface TourService<E> {

    List<E> getAllTours();

    Tour getTourById(int id);
}
