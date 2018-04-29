package model.dao.impl;

import model.dao.interfaces.UserDAO;
import model.pojo.User;
import model.utils.DataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.sql.*;
import java.util.List;

/**
 * Created by admin on 20.04.2017.
 */
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    @Override
    public User findUserByLoginAndPassword(String login, String password) {

        LOGGER.debug("UserDAO. findUserByLoginAndPassword.");

        User user = null;
        String sql = "SELECT * FROM Users WHERE Login = ? AND Password = ?;";

        LOGGER.debug("Get user with login: " + login + "; and password: " + password + "; from Users");

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

            LOGGER.error("SQLException", e);
        }

        return user;
    }

    @Override
    public void insert(User entity) {

        LOGGER.debug("UserDAO. insert.");
        String sql = "INSERT INTO users(Login, Password) VALUES (?, ?);";

        LOGGER.debug("Set user with login: " + entity.getLogin() + "; and password: " + entity.getPassword() + "; into Users");

        try(Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.execute();

            statement.close();
            connection.close();
        } catch (SQLException e) {

            LOGGER.error("SQLException ",e);
        }
    }

    @Override
    public User getById(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public User save(User entity) {
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

}