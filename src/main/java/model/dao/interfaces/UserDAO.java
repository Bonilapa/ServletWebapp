package model.dao.interfaces;

import model.pojo.User;

import java.util.List;

public interface UserDAO extends DAO<User, Integer> {
    User findUserByLoginAndPassword(String login, String password);

    @Override
    List<User> getAll();

    @Override
    void insert(User entity);
}