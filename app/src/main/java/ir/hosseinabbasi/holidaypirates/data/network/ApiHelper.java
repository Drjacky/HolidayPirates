package ir.hosseinabbasi.holidaypirates.data.network;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

import java.util.List;

import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiHelper {

    //@GET("posts")
    Observable<List<Posts>> doPostsListApiCall();

    @GET("posts/{postId}/comments")
    Observable<List<Comments>> doCommentsListApiCall(@Path("postId") String postId);
    //Observable<List<Comments>> doCommentsListApiCall(@Path("postId") String postId); //Retrofit


    //Observable<List<Comments>> doCommentsListApiCall(String postId); //But I used Fast-Android-Networking instead.
}
