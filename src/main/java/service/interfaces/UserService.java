package service.interfaces;

import model.pojo.User;


/**
 * Created by admin on 20.04.2017.
 */
public interface UserService<E> {

    Integer addUser(User user);
}