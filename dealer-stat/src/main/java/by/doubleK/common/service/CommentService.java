package by.doubleK.common.service;

import by.doubleK.common.entity.Comment;
import by.doubleK.common.entity.User;
import by.doubleK.common.repository.CommentRepository;
import by.doubleK.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {


    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean saveComment(String username, Comment comment) {
        User user = userRepository.getByUsername(username);
        if (user != null && user.isActivated()) {
            setUser(user, comment);
            commentRepository.save(comment);
            return true;
        } else {
            return false;
        }
    }


    @Transactional
    public List<Comment> getUserComments(String username) {
        return commentRepository.getAllByUser(userRepository.getByUsername(username))
                .stream().filter(Comment::isApproved)
                .filter(comment -> comment.getUser().isActivated())
                .collect(Collectors.toList());
    }


    @Transactional
    public void setUser(User user, Comment comment) {
        comment.setUser(user);
    }

    @Transactional
    public void setCommentApprove(Long id) {
        Comment comment = commentRepository.getById(id);
        comment.setApproved(!comment.isApproved());
        commentRepository.save(comment);
    }
}
