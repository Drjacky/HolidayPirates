package ir.hosseinabbasi.holidaypirates.data.db;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ir.hosseinabbasi.holidaypirates.data.db.model.DaoMaster;
import ir.hosseinabbasi.holidaypirates.data.db.model.DaoSession;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;


/**
 * Created by Dr.jacky on 2017/07/13.
 * Database management and all the data handling related to a database is done in this part of the application.
 * AppDbHelper class will be used by DataManager to access the SQLite database.
 */

@Singleton /*@Singleton ensure a single instance of a class globally.*/
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<Posts>> getAllPosts() {
        return Observable.fromCallable(new Callable<List<Posts>>() {
            @Override
            public List<Posts> call() throws Exception {
                return mDaoSession.getPostsDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isPostsEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getPostsDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> savePosts(final Posts p) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getPostsDao().insert(p);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> savePostsList(final List<Posts> postsList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getPostsDao().insertInTx(postsList);
                return true;
            }
        });
    }
}
