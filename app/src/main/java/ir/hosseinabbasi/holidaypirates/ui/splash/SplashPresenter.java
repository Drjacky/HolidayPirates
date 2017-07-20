package ir.hosseinabbasi.holidaypirates.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.androidnetworking.error.ANError;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.hosseinabbasi.holidaypirates.R;
import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.ui.base.BasePresenter;
import ir.hosseinabbasi.holidaypirates.utils.rx.SchedulerProvider;

/**
 * Created by Dr.jacky on 2017/07/13.
 * It is the decision-making counterpart of the View and is a pure java class, with no access to Android APIs.
 * It receives the user interactions passed on from its View and then takes the decision based on the business logic, finally instructing the View to perform specific actions.
 * It also communicates with the DataManager for any data it needs to perform business logic.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        //getMvpView().startSyncService();

        //Fix this! Move this scope to onViewInitialized
        getCompositeDisposable().add(getDataManager()
                .doPostsListApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Posts>>() {
                    @Override
                    public void accept(List<Posts> response) throws Exception {

                        /*getDataManager().insertIntoDB( //Insert Retrieved Data to Local Database for Offline Use.
                                response.getAccessToken(),
                                response.getUserId(), ...*/

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        decideNextActivity(response);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));

    }

    private void decideNextActivity(final List<Posts> response) {
        new Handler().postDelayed(new Runnable() { //This postDelay is not necessary; Just to show the beautiful Splash
            public void run() {
                getMvpView().openMainActivityWithPostsData(response);
            }
        }, 5500);

    }
}
