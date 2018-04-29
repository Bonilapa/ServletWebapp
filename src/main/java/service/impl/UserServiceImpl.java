package service.impl;

import model.dao.impl.LoginDAOImpl;
import model.dao.impl.UserDAOImpl;
import model.pojo.User;
import model.utils.Valid;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import service.interfaces.UserService;

/**
 * Created by admin on 20.04.2017.
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private LoginDAOImpl loginDAO = new LoginDAOImpl();
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public Valid.Error addUser(User user) {

        LOGGER.debug("UserService. addUser.");

        if(user.getLogin() == ""){

            LOGGER.debug("Empty login put.");
            return Valid.Error.EmptyLogin;

        }else if(user.getPassword() == ""){

            LOGGER.debug("Empty password put.");
            return Valid.Error.EmptyPassword;

        }else{

            User isUser = loginDAO.getUserByLogin(user.getLogin());

            if(isUser == null) {

                LOGGER.debug("Add new user:" + user.getLogin());
                userDAO.insert(user);
                return Valid.Error.Correct;

            }else{

                LOGGER.debug("User: " + user.getLogin() + " is already exists.");
                return Valid.Error.Correct;
            }

        }

    }

}