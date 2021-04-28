package by.doubleK.common.dao.hibernateimpl;

import by.doubleK.common.dao.AdvertDao;
import by.doubleK.common.entity.Advert;
import by.doubleK.common.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class HibernateAdvertDao implements AdvertDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Advert advert) {
//        if (advert.getUser() != null){
//            entityManager.persist(advert.getUser());
//        }

        Long id = advert.getId();
        if (id == null) {
            entityManager.persist(advert);
        } else {
            entityManager.merge(advert);
        }
    }


    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }


    @Override
    public Advert getById(Long id) {
        return entityManager.find(Advert.class, id);
    }


    @Override
    public List<Advert> getAll() {
        return (List<Advert>) entityManager.createNativeQuery("SELECT * FROM advert").getResultList();
    }

    @Override
    public List<Advert> getByUser(User user) {
        return  entityManager.createNativeQuery("SELECT * FROM advert WHERE id="+user.getId()).getResultList();
    }
}
