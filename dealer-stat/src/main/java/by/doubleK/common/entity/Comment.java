package by.doubleK.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private int mark;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "approved")
    private boolean isApproved;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
