package service.impl;

import model.dao.impl.LoginDAOImpl;
import model.dao.impl.UserDAOImpl;
import model.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.interfaces.LoginService;

import java.util.List;

public class LoginServiceImpl implements LoginService{

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private LoginDAOImpl loginDAO = new LoginDAOImpl();
    private UserDAOImpl userDAO = new UserDAOImpl();
    @Override
    public User auth(String login, String password){
        User user = loginDAO.getUserByLoginAndPassword(login, password);

        LOGGER.debug("user: " + user);
        if((user == null)){
            return null;
        }
        LOGGER.debug(" exists ");
        System.out.println("exists");
        return user;
    }
}
