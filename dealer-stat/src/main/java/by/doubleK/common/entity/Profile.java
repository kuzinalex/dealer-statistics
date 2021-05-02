package by.doubleK.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private User user;
    private List<Advert> advertList;
    private List<Comment> commentsList;
    private double rating;

    public Profile(User user, List<Advert> advertList, List<Comment> commentsList) {
        this.user = user;
        this.advertList = advertList;
        this.commentsList = commentsList;
        this.rating = calculateAverageRating();
    }

    private double calculateAverageRating() {
        if (commentsList.size() !=0) {
            double averageRating = 0;

            for (Comment comment : commentsList) {
                averageRating += comment.getMark();
            }
            return averageRating / (double) commentsList.size();
        }
        else {
            return 0;
        }
    }
}
