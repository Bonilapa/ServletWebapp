package service;

import connector.LoginServlet;
import model.dao.impl.UserDAOImpl;
import model.pojo.User;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by admin on 20.04.2017.
 */
public class UserServiceImpl implements UserService {
    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader().getResource("log4j.properties"));
    }

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private static UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public User auth(String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);

        //LOGGER.debug("user: " + user);

        if((user != null)){
            return null;
        }

        //LOGGER.debug("user not blocked ");

        return user;
    }
}
