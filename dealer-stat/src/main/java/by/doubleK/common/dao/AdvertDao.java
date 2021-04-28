package by.doubleK.common.dao;

import by.doubleK.common.entity.Advert;
import by.doubleK.common.entity.User;

import java.util.List;

public interface AdvertDao {
    void save(Advert advert);

    void delete(Long id);

    Advert getById(Long id);

    List<Advert> getAll();

    List<Advert> getByUser(User user);
}
