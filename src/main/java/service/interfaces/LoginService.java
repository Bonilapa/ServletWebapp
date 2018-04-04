package service.interfaces;

import model.pojo.User;

public interface LoginService<E> {
    User auth(String login, String password);
}
