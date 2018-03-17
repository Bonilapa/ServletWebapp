package model.dao.interfaces;

import model.pojo.User;

import java.util.List;

public interface UserDAO extends DAO<User, Long> {
    User findUserByLoginAndPassword(String login, String password);

    @Override
     List<User> getAll();
}