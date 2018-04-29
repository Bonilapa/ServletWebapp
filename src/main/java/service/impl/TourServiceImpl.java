package service.impl;

import model.dao.impl.TourDAOImpl;
import model.pojo.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.interfaces.TourService;

import java.util.List;

public class TourServiceImpl implements TourService {

    private static final Logger LOGGER = LogManager.getLogger(TourServiceImpl.class);
    private TourDAOImpl tourDAO = new TourDAOImpl();

    @Override
    public List<Tour> getAllTours(){

        LOGGER.debug("TourService. getAllTours.");
        return tourDAO.getAll();
    }

    @Override
    public Tour getTourById(int id) {

        LOGGER.debug("TourService. getTourById: " + id);
        return tourDAO.getById(id);
    }

}