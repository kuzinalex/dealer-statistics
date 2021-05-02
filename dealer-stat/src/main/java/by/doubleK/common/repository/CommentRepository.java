package by.doubleK.common.repository;

import by.doubleK.common.entity.Comment;
import by.doubleK.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getAllByUser(User user);
}
