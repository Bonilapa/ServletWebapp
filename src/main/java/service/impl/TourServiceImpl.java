package service.impl;

import model.dao.impl.TourDAOImpl;
import model.pojo.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.interfaces.TourService;

import java.util.List;

public class TourServiceImpl implements TourService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private TourDAOImpl tourDAO = new TourDAOImpl();

    @Override
    public List<Tour> getAllTours(){
        //System.setProperty("log4j2.debug", "http://gate.ac.uk/wiki/code-repository");
        return tourDAO.getAll();
    }

    @Override
    public Tour getTourById(int id) {
        return tourDAO.getById(id);
    }
}
