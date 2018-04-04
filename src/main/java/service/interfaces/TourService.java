package service.interfaces;

import model.pojo.Tour;
import java.util.List;

public interface TourService<E> {
    public List<E> getAllTours();
    public Tour getTourById(int id);
}
