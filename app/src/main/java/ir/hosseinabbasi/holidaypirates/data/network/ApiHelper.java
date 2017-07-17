package ir.hosseinabbasi.holidaypirates.data.network;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

import java.util.List;

import io.reactivex.Observer;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.db.model.Users;
import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiHelper {

    Observable<List<Posts>> doPostsListApiCall(); //For Fast-Android-Networking; To show with MVP, all of the elements are pluggable

    @GET("posts/{postId}/comments")
    Observable<List<Comments>> getComments(@Path("postId") String postId);

    @GET("users/{userId}")
    Observable<Users> getUser(@Path("userId") String userId);
}
