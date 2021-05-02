package by.doubleK.common.controller;

import by.doubleK.common.entity.Comment;
import by.doubleK.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }

    @PostMapping("/users/{username}/comments")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable String username) {
        if (commentService.saveComment(username, comment)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/users/{username}/comments")
    public ResponseEntity<List<Comment>> allUserComments(@PathVariable String username) {

        return ResponseEntity.ok(commentService.getUserComments(username));
    }


    @PutMapping("/comment/approve/{id}")
    public ResponseEntity<Comment> approveAdvert(@PathVariable Long id) {
        commentService.setCommentApprove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
