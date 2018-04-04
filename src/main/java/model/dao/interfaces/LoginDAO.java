package model.dao.interfaces;

import model.pojo.User;

import java.util.List;

public interface LoginDAO extends DAO<User, Long> {
    User getUserByLoginAndPassword(String login, String password);
    @Override
    List<User> getAll();
}
