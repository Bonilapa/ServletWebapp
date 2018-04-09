package model.dao.impl;

import connector.LoginServlet;
import model.dao.interfaces.UserDAO;
import model.pojo.User;
import model.utils.DataSourceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by admin on 20.04.2017.
 */
public class UserDAOImpl implements UserDAO {
//    static {
//        PropertyConfigurator.configure(LoginServlet.class.getClassLoader().getResource("log4j.properties"));
//    }

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE Login = ? AND Password = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            user = new User(resultSet.getInt("id"), resultSet.getString("login"),
                    resultSet.getString("password"));

            //LOGGER.debug("got user");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            //LOGGER.debug(e);
        }

//        if (group == null) { //TODO Надо что ли?
//            throw new SQLException("Record with PK = " + id + " not found.");
//        }

        return user;
    }

    @Override
    public User getById(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("password"));
                list.add(user);
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
    public User save(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(User entity) {
        String sql = "INSERT INTO users(Login, Password) VALUES (?, ?);";
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            System.out.println(entity.getLogin());
            System.out.println(entity.getPassword());
            Boolean bol = statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
