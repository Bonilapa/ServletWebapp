package service.interfaces;

import model.pojo.User;
import model.utils.Valid;


/**
 * Created by admin on 20.04.2017.
 */
public interface UserService<E> {

    Valid.Error addUser(User user);
}