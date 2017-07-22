package ir.hosseinabbasi.holidaypirates.ui.main;

import android.util.Log;

import com.androidnetworking.error.ANError;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.CommentsUserCombined;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.db.model.Users;
import ir.hosseinabbasi.holidaypirates.data.network.ApiEndPoint;
import ir.hosseinabbasi.holidaypirates.data.network.ApiHelper;
import ir.hosseinabbasi.holidaypirates.data.network.ApiUtils;
import ir.hosseinabbasi.holidaypirates.ui.base.BasePresenter;
import ir.hosseinabbasi.holidaypirates.utils.rx.SchedulerProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    public void onPostsItemClicked(String postId, final String userId) {
        getMvpView().showLoading();
        Observable<List<Comments>> mCommentsObservable = ApiUtils.getJsonPlaceHolderService().getComments(postId);
        Observable<Users> mUsersObservable = ApiUtils.getJsonPlaceHolderService().getUser(userId);

        Observable.zip(mCommentsObservable, mUsersObservable,
                new BiFunction<List<Comments>, Users, List<Object>>() {
            @Override
            public List<Object> apply(@NonNull List<Comments> comments, @NonNull Users users) throws Exception {
                List<Object> combinedList = new ArrayList<>();
                combinedList.addAll(comments);
                combinedList.add(users);
                return combinedList;
            }
        })
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(openDetailActivityWithDataObserver());
    }

    private Observer<List<Object>> openDetailActivityWithDataObserver() {
        return new Observer<List<Object>>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<Object> combinedList) {
                getMvpView().hideLoading();
                getMvpView().openDetailActivityWithData(combinedList);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        };
    }
}
