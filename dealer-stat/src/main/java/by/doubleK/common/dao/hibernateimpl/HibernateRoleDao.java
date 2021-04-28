package by.doubleK.common.dao.hibernateimpl;

import by.doubleK.common.dao.RoleDao;
import by.doubleK.common.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class HibernateRoleDao implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        Long id = role.getId();
        if (id == null) {
            entityManager.persist(role);
        } else {
            entityManager.merge(role);
        }
    }

    @Override
    public void delete(Long id) {
        Role role=getById(id);
        entityManager.remove(role);
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class,id);
    }

    @Override
    public List<Role> getAll() {
        return (List<Role>) entityManager.createNativeQuery("SELECT * FROM role").getResultList();
    }
}
