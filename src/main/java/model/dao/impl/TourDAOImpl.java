package model.dao.impl;

import model.dao.interfaces.TourDAO;
import model.pojo.Tour;
import model.pojo.User;
import model.utils.DataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDAOImpl implements TourDAO{
//    static {
//        PropertyConfigurator.configure(LoginServlet.class.getClassLoader().getResource("log4j.properties"));
//    }

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    @Override
    public Tour getById(Integer id) {
        Tour tour = null;
        String sql = "SELECT * FROM tours WHERE ID = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            tour = new Tour(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getDate("date"),
                    resultSet.getString("description"));

            //LOGGER.debug("got user");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            //LOGGER.debug(e);
        }
        return tour;
    }

    @Override
    public int getTourAmount() {
        int tourAmount = 0;
        String sql = "SELECT * FROM table ORDER BY id DESC LIMIT 1";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            tourAmount = resultSet.getInt("id");
            //LOGGER.debug("got user");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            //LOGGER.debug(e);
        }
        return tourAmount;
    }

    @Override
    public List<Tour> getAll() {
        List<Tour> list = new ArrayList<>();
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
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
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Tour save(Tour entity) {
        throw new NotImplementedException();
    }

    @Override
    public Integer insert(Tour entity) {
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