package by.doubleK.common.service;

import by.doubleK.common.dao.AdvertDao;
import by.doubleK.common.entity.Advert;
import by.doubleK.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdvertService {

    private final AdvertDao advertDao;

    @Autowired
    public AdvertService(AdvertDao advertDao) {
        this.advertDao = advertDao;
    }


    public void save(Advert user) {
        advertDao.save(user);
    }


    public Advert getById(Long id) {
        return advertDao.getById(id);
    }


    public void delete(Long id) {
        advertDao.delete(id);
    }


    public List<Advert> getAll() {
        return advertDao.getAll();
    }


    public List<Advert> getByUser(User user) {
        return advertDao.getByUser(user);
    }

}
