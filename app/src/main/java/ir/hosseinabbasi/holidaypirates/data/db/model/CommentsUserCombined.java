package ir.hosseinabbasi.holidaypirates.data.db.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dr.jacky on 7/18/2017.
 */

public class CommentsUserCombined {
    @SerializedName("user")
    private Users user;

    @SerializedName("comments")
    private Comments comments;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CommentsUserCombined{" +
                "user=" + user +
                ", comments=" + comments +
                '}';
    }
}
