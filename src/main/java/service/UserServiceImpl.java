package service;

import connector.LoginServlet;
import model.dao.impl.UserDAOImpl;
import model.dao.interfaces.UserDAO;
import model.pojo.User;
//import org.apache.logging.log4j..PropertyConfigurator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Created by admin on 20.04.2017.
 */
public class UserServiceImpl implements UserService {
//    static {
//        PropertyConfigurator.configure(LoginServlet.class.getClassLoader().getResource("log4j.properties"));
//    }
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public User auth(String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);

        LOGGER.debug("user: " + user);
        if((user != null)){
            return null;
        }
        LOGGER.debug("user not blocked ");
        return user;
    }
    @Override
    public List<User> getAllUsers(){
        //System.setProperty("log4j2.debug", "http://gate.ac.uk/wiki/code-repository");
        return userDAO.getAll();
    }
}
