package model.dao.impl;

import model.dao.interfaces.OrderDAO;
import model.pojo.Order;
import model.pojo.Tour;
import model.utils.DataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);

    @Override
    public Tour getByTourId(Integer userId, Integer tourId) {

        LOGGER.debug("OrderDAO. getTourById.");

        Tour tour = null;
        String sql = "SELECT tours.* FROM tours, orders WHERE orders.userId =  ? AND orders.tourId = ? AND orders.tourId=tours.id;";

        try {

            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, tourId);

            LOGGER.debug("Get tour: " + tourId + " for userId: " + userId);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            tour = new Tour(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getDate("date"),
                    resultSet.getString("description"));

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            LOGGER.error(e);
        }

        return tour;
    }

    @Override
    public List<Tour> getByUserId(Integer userId) {

        LOGGER.debug("OrderDAO. getByUserId.");

        List<Tour> list = new ArrayList<>();
        String sql = "SELECT tours.* FROM tours, orders WHERE userId = ? AND tours.ID = orders.tourId;";

        try {

            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            LOGGER.debug("Get all tours for userId: " + userId);

            ResultSet resultSet = statement.executeQuery();

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

            LOGGER.error(e);
        }

        return list;
    }

    @Override
    public Boolean insertOrder(Order entity, Integer userId) {

        LOGGER.debug("OrderDAO. insertOrder.");

        String sql = "INSERT INTO orders(tourId, userId) VALUES (?, ?);";

        try {

            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getTour().getId());
            statement.setInt(2, userId);

            LOGGER.debug("Set tour: " + entity.getTour() + " for userId: " + userId);

            statement.execute();

            statement.close();
            connection.close();

        } catch (SQLException e) {

            LOGGER.error(e);
            return false;
        }

        return true;
    }

    @Override
    public Order save(Order entity) {
        throw new NotImplementedException();
    }

    @Override
    public int update(Order entity) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(Order entity) {
        throw new NotImplementedException();
    }

    @Override
    public int delete(Order entity) {
        throw new NotImplementedException();
    }

    @Override
    public Order getById(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Order> getAll() {
        throw new NotImplementedException();
    }

}