package ir.hosseinabbasi.holidaypirates.data.db;

import java.util.List;

import io.reactivex.Observable;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;

/**
 * Created by Dr.jacky on 2017/07/13.
 * It is an interface implemented by the AppDbHelper and contains methods exposed to the application components. This layer decouples any specific implementation of the DbHelper and hence makes AppDbHelper as plug and play unit.
 */

public interface DbHelper {
    Observable<List<Posts>> getAllPosts();
    Observable<Boolean> isPostsEmpty();
    Observable<Boolean> savePosts(Posts p);
    Observable<Boolean> savePostsList(List<Posts> postList);
}
