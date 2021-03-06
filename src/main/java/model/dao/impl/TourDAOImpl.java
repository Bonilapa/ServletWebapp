package model.dao.impl;

import model.dao.interfaces.TourDAO;
import model.pojo.Tour;
import model.utils.DataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDAOImpl implements TourDAO{

    private static final Logger LOGGER = LogManager.getLogger(TourDAOImpl.class);

    @Override
    public Tour getById(Integer id) {

        LOGGER.debug("TourDAO. getById");

        Tour tour = null;
        String sql = "SELECT * FROM tours WHERE ID = ?;";

        LOGGER.debug("Get tour with id: " + id + " from Tours");

        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            tour = new Tour(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getDate("date"),
                    resultSet.getString("description"));

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            LOGGER.error("SQLException ", e);
        }

        return tour;
    }

    @Override
    public int getTourAmount() {

        LOGGER.debug("TourDAO. getTourAmount.");

        int tourAmount = 0;
        String sql = "SELECT * FROM table ORDER BY id DESC LIMIT 1";
        LOGGER.debug("Get tour amount from Tours");

        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            tourAmount = resultSet.getInt("id");

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            LOGGER.error("ESLException ", e);
        }

        return tourAmount;
    }

    @Override
    public List<Tour> getAll() {

        LOGGER.debug("TourDAO. getAll.");

        List<Tour> list = new ArrayList<>();

        LOGGER.debug("Get all tours from Tours");

        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tours;");
            while (resultSet.next()) {
                Tour tour = new Tour(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getDate("date"));
                list.add(tour);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            LOGGER.error("SQKException ", e);
        }

        return list;
    }

    @Override
    public Tour save(Tour entity) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(Tour entity) {
        throw new NotImplementedException();
    }

    @Override
    public int update(Tour entity) {
        throw new NotImplementedException();
    }

    @Override
    public int delete(Tour entity) {
        throw new NotImplementedException();
    }

}