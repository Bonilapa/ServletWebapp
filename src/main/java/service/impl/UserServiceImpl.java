package service.impl;

import model.dao.impl.UserDAOImpl;
import model.pojo.User;
//import org.apache.logging.log4j..PropertyConfigurator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import service.interfaces.UserService;

import java.util.List;

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
    @Override
    public Integer addUser(User user) {
        if (user.getPassword() != "" && user.getLogin() != "") {
            userDAO.insert(user);
            return 0;
        }else if(user.getLogin() == ""){
            return 1;
        }else{
            return -1;
        }
    }
}
