package model.dao.impl;

import model.dao.interfaces.LoginDAO;
import model.pojo.User;
import model.utils.DataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    private static final Logger LOGGER = LogManager.getLogger(LoginDAOImpl.class);

    @Override
    public User getUserByLoginAndPassword(String login, String password) {


        User user = null;
        String sql = "SELECT * FROM Users WHERE Login = ? AND Password = ?;";

        LOGGER.debug("Attempt to take user "+login+" from table Users");

        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getInt("id"), resultSet.getString("login"),
                    resultSet.getString("password"));

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("SQLException ", e);
        }
        return user;
    }
    @Override
    public User getUserByLogin(String login){

        LOGGER.debug("LoginDAO. getUserById.");

        User user = null;
        String sql = "SELECT * FROM Users WHERE Login = ?;";

        LOGGER.debug("Take user " + login + " from table Users");

        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getInt("id"), resultSet.getString("login"),
                    resultSet.getString("password"));

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            LOGGER.error("SQLException ", e);
        }

        return user;
    }

    @Override
    public List<User> getAll(){throw new NotImplementedException();}

    @Override
    public User save(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public int update(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public int delete(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public User getById(Long id) {
        throw new NotImplementedException();
    }

}
