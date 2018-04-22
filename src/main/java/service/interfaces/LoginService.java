package service.interfaces;

import model.pojo.User;

public interface LoginService<E> {
    String auth(String login, String password);
}
