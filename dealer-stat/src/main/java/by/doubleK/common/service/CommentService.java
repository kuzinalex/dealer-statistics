package by.doubleK.common.service;

import by.doubleK.common.dao.CommentDao;
import by.doubleK.common.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }


    public void save(Comment comment) {
        commentDao.save(comment);
    }


    public Comment getById(Long id) {
        return commentDao.getById(id);
    }


    public void delete(Long id) {
        commentDao.delete(id);
    }


    public List<Comment> getAll() {
        return commentDao.getAll();
    }
}
