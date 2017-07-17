package ir.hosseinabbasi.holidaypirates.ui.main;


import ir.hosseinabbasi.holidaypirates.di.PerActivity;
import ir.hosseinabbasi.holidaypirates.ui.base.MvpPresenter;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onViewInitialized();
    void onPostsItemClicked(String postId, String userId);
}
