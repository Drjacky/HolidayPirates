package ir.hosseinabbasi.holidaypirates.data.network;

import android.net.Uri;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.db.model.Users;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.Observable;
import retrofit2.http.Path;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<List<Posts>> doPostsListApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_JSONPLACEHOLDER_POSTS)
                .build()
                //.getJSONArrayObservable() //Fix this! To make Splash data -> Main, better
                .getObjectListObservable(Posts.class);
    }

    @Override
    public Observable<List<Comments>> getComments(@Path("postId") String postId) {
        return null;
    }

    @Override
    public Observable<Users> getUser(@Path("userId") String userId) {
        return null;
    }
}

