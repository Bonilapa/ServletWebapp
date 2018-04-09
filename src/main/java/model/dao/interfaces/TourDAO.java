package model.dao.interfaces;

import model.pojo.Tour;
import model.pojo.User;

import java.util.List;

public interface TourDAO extends DAO<Tour, Integer> {
    @Override
    Tour getById(Integer tourId);

    int getTourAmount();

    @Override
    List<Tour> getAll();
    @Override
    void insert(Tour tour);
}