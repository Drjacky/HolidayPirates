package ir.hosseinabbasi.holidaypirates.ui.splash;

import java.util.List;

import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.ui.base.MvpView;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

public interface SplashMvpView extends MvpView {
    void openMainActivity();
    void openMainActivityWithPostsData(List<Posts> posts);
    void startSyncService();
}
