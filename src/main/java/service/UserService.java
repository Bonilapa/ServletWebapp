package service;

import model.dao.interfaces.UserDAO;
import model.pojo.User;

import java.util.List;

/**
 * Created by admin on 20.04.2017.
 */
public interface UserService<E> {
    User auth(String login, String password);
    public List<E> getAllUsers();
}
