package model.dao.interfaces;

import model.pojo.User;

import java.util.List;

public interface LoginDAO extends DAO<User, Long> {
    User getUserByLoginAndPassword(String login, String password);
    User getUserByLogin(String login);
    @Override
    List<User> getAll();
    @Override
    void insert(User entity);
}
