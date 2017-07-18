package ir.hosseinabbasi.holidaypirates.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import ir.hosseinabbasi.holidaypirates.data.db.DbHelper;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.db.model.Users;
import ir.hosseinabbasi.holidaypirates.data.network.ApiHelper;
import ir.hosseinabbasi.holidaypirates.di.ApplicationContext;
import retrofit2.http.Path;

/**
 * Created by Dr.jacky on 2017/07/13.
 * AppDataManager class will provide access to the data of the application.
 * DbHelper and ApiHelper only work for DataManager.
 */

@Singleton
public class AppDataManager implements DataManager, ApiHelper {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final ApiHelper mApiHelper;

    @Inject /*@Inject on the constructor instructs the Dagger to accumulate all the parameter dependencies when the class is being constructed.
    @ApplicationContext Qualifier facilitates AppDataManager to get the context object of the application from dagger’ApiHelper dependency graph.
    */
    /*Or @Named("application_context")*/ /* $$$ */
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<Boolean> isPostsEmpty() {
        return mDbHelper.isPostsEmpty();
    }

    @Override
    public Observable<Boolean> savePosts(Posts posts) {
        return mDbHelper.savePosts(posts);
    }

    @Override
    public Observable<Boolean> savePostsList(List<Posts> postList) {
        return mDbHelper.savePostsList(postList);
    }

    @Override
    public Observable<List<Posts>> getAllPosts() {
        return mDbHelper.getAllPosts();
    }

    /*@Override //To Insert Posts To Local DB
    public Observable<Boolean> fillDatabasePosts() {
        return mDbHelper.isPostEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types
                                    .newParameterizedTypeWithOwner(null, List.class,
                                            Posts.class);


                            JsonParser parser = new JsonParser();
                            JsonObject rootObject = parser.parse(JSONHERE)).getAsJsonObject();
                            JsonElement dataElement = rootObject.get("");

                            Gson gson = new Gson();
                            List<Posts> dataList = new ArrayList<>();
                            //Check if "data" element is an array or an object and parse accordingly...
                            if (dataElement.isJsonObject()) {
                                //The returned list has only 1 element
                                Posts d = gson.fromJson(dataElement, Posts.class);
                                dataList.add(d);
                            }
                            else if (dataElement.isJsonArray()) {
                                //The returned list has >1 elements
                                Type dataListType = new TypeToken<List<Posts>>() {}.getType();
                                dataList = gson.fromJson(dataElement, dataListType);
                            }

                            return savePostList(dataList);
                        }
                        return Observable.just(false);
                    }
                });
    }*/

    @Override
    public Observable<List<Posts>> doPostsListApiCall() {
        return mApiHelper.doPostsListApiCall();
    }

    @Override
    public Observable<List<Comments>> getComments(@Path("postId") String postId) {
        return mApiHelper.getComments(postId);
    }

    @Override
    public Observable<Users> getUser(@Path("userId") String userId) {
        return mApiHelper.getUser(userId);
    }
}
