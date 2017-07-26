package ir.hosseinabbasi.holidaypirates.di.module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.hosseinabbasi.holidaypirates.data.network.ApiEndPoint;
import ir.hosseinabbasi.holidaypirates.di.ActivityContext;
import ir.hosseinabbasi.holidaypirates.di.PerActivity;
import ir.hosseinabbasi.holidaypirates.ui.detail.DetailMvpPresenter;
import ir.hosseinabbasi.holidaypirates.ui.detail.DetailMvpView;
import ir.hosseinabbasi.holidaypirates.ui.detail.DetailPresenter;
import ir.hosseinabbasi.holidaypirates.ui.main.MainMvpPresenter;
import ir.hosseinabbasi.holidaypirates.ui.main.MainMvpView;
import ir.hosseinabbasi.holidaypirates.ui.main.MainPresenter;
import ir.hosseinabbasi.holidaypirates.ui.splash.SplashMvpPresenter;
import ir.hosseinabbasi.holidaypirates.ui.splash.SplashMvpView;
import ir.hosseinabbasi.holidaypirates.ui.splash.SplashPresenter;
import ir.hosseinabbasi.holidaypirates.utils.rx.AppSchedulerProvider;
import ir.hosseinabbasi.holidaypirates.utils.rx.SchedulerProvider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

@Module /*To provide the dependency for a class we have to create a Module class. This class defines the methods that provide the dependency.*/
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides /*The dependency provider method*/
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView>
                                                                     presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView>
                                                               presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DetailMvpPresenter<DetailMvpView> provideDetailPresenter(DetailPresenter<DetailMvpView>
                                                               presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiEndPoint.ENDPOINT_JSONPLACEHOLDER)
                .build();

        return retrofit;
    }
}
