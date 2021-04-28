package by.doubleK.common.dao.hibernateimpl;

import by.doubleK.common.dao.CommentDao;
import by.doubleK.common.entity.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class HibernateCommentDao implements CommentDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Comment comment) {
        Long id = comment.getId();
        if (id == null) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
    }


    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }


    @Override
    public Comment getById(Long id) {
        return entityManager.find(Comment.class, id);
    }


    @Override
    public List<Comment> getAll() {
        return (List<Comment>) entityManager.createNativeQuery("SELECT * FROM comment").getResultList();
    }
}
