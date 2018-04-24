package service.impl;

import model.dao.impl.LoginDAOImpl;
import model.dao.impl.UserDAOImpl;
import model.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.interfaces.LoginService;

import java.util.List;

public class LoginServiceImpl implements LoginService{

    private static final Logger LOGGER = LogManager.getLogger(LoginServiceImpl.class);
    private LoginDAOImpl loginDAO = new LoginDAOImpl();

    @Override
    public User auth(String login, String password){

        LOGGER.debug("LoginService. auth.");

        User user = loginDAO.getUserByLoginAndPassword(login, password);


        if((user == null)){

            LOGGER.debug("User: "+ login + " does not exist.");
            return null;
        }

        LOGGER.debug("user: " + user.getLogin());
        return user;
    }

}