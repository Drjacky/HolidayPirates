package ir.hosseinabbasi.holidaypirates.ui.main;

import android.util.Log;

import com.androidnetworking.error.ANError;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.network.ApiEndPoint;
import ir.hosseinabbasi.holidaypirates.data.network.ApiHelper;
import ir.hosseinabbasi.holidaypirates.data.network.ServiceFactory;
import ir.hosseinabbasi.holidaypirates.ui.base.BasePresenter;
import ir.hosseinabbasi.holidaypirates.utils.rx.SchedulerProvider;
import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;


/**
 * Created by Dr.jacky on 2017/07/13.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewInitialized() {
        getCompositeDisposable().add(getDataManager()
                .getAllPosts()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Posts>>() {
                    @Override
                    public void accept(List<Posts> postsList) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (postsList != null) {
                            getMvpView().refreshPostsList(postsList);
                        }
                    }
                }));
    }

    @Override
    public void onPostsItemClicked(String postId) {
        final ApiHelper service = ServiceFactory.createRetrofitService(ApiHelper.class, ApiEndPoint.ENDPOINT_JSONPLACEHOLDER_COMMENTS);
        Observer obs = service.doCommentsListApiCall(postId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(getLoginObserver());

    }

    public Observer<List<Comments>> getLoginObserver() {
        return new Observer<List<Comments>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<Comments> commentses) {
            Log.wtf("value",commentses.toString());
        }

        @Override
        public void onError(Throwable e) {
            Log.wtf("value",e.toString());
        }

        @Override
        public void onComplete() {

        }
    };



        /*ApiHelper service = ServiceFactory.createRetrofitService(ApiHelper.class, (ApiEndPoint.ENDPOINT_JSONPLACEHOLDER_POSTS));
        service.doCommentsListApiCall(postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                        new DisposableObserver<List<Comments>>() {

                            @Override
                            public void onComplete() {
                                Timber.d("Retrofit call 1 completed");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Timber.e(e, "woops we got an error while getting the list of contributors");
                            }

                            @Override
                            public void onNext(List<Comments> contributors) {
                                for (Comments c : contributors) {
                                    Log.wtf("contributors",contributors.toString());
                                }
                            }
                        });*/


        /*Log.wtf("5",postId+" ");
        getCompositeDisposable().add(getDataManager()
                .doCommentsListApiCall(postId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Comments>>() {
                    @Override
                    public void accept(List<Comments> response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        Log.wtf("6","6");
                        getMvpView().hideLoading();
                        Log.wtf("MainPres_onPostItm",response.toString());
                        getMvpView().openDetailActivityWithData();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        Log.wtf("-6","-6");
                        getMvpView().hideLoading();
                        Log.wtf("MainPres_Throwable",throwable.getMessage());
                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));*/
    }
}
