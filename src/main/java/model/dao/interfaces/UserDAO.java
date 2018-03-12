package model.dao.interfaces;

import model.pojo.User;

public interface UserDAO extends DAO<User, Long> {
    User findUserByLoginAndPassword(String login, String password);
}