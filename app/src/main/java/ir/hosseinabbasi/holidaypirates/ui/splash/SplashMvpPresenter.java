package ir.hosseinabbasi.holidaypirates.ui.splash;

import ir.hosseinabbasi.holidaypirates.di.PerActivity;
import ir.hosseinabbasi.holidaypirates.ui.base.MvpPresenter;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
    void onViewInitialized();
}
