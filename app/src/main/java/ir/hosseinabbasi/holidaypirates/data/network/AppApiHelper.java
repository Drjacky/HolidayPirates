package ir.hosseinabbasi.holidaypirates.data.network;

import android.net.Uri;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.Observable;
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
                //.getObjectObservable(Posts.class);

        /*return new Retrofit
                .Builder()
                .baseUrl(ApiEndPoint.ENDPOINT_JSONPLACEHOLDER_POSTS)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiHelper.class).getPostsList();*/
    }

    @Override
    public Observable<List<Comments>> doCommentsListApiCall(String postId) {

        /*Log.wtf("7",postId+" ");
        final String mPId = postId;
        //return Rx2AndroidNetworking.get("http://jsonplaceholder.typicode.com/posts/{pid}/comments")
        return Rx2AndroidNetworking.get("http://jsonplaceholder.typicode.com/posts/1/comments") //Fix this! Really?!
                //.addPathParameter("pid", mPId)
                .build()
                .getObjectListObservable(Comments.class); //Fix this!*/

        return new Retrofit
                .Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/posts/1/comments")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiHelper.class).doCommentsListApiCall(postId);
    }
}

