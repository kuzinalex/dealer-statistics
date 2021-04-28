package by.doubleK.common.dao;

import by.doubleK.common.entity.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    void delete(Long id);

    User getById(Long id);

    List<User> getAll();
}
