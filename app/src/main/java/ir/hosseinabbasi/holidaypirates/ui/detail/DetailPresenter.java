package ir.hosseinabbasi.holidaypirates.ui.detail;

import android.os.Handler;
import android.util.Log;

import com.androidnetworking.error.ANError;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Photos;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.data.network.ApiHelper;
import ir.hosseinabbasi.holidaypirates.data.network.ApiUtils;
import ir.hosseinabbasi.holidaypirates.ui.base.BasePresenter;
import ir.hosseinabbasi.holidaypirates.utils.rx.SchedulerProvider;
import retrofit2.Retrofit;

/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */

public class DetailPresenter<V extends DetailMvpView> extends BasePresenter<V>
        implements DetailMvpPresenter<V> {

    @Inject
    public DetailPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable,
                           Retrofit retrofit) {
        super(dataManager, schedulerProvider, compositeDisposable, retrofit);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onViewInitialized() {
        getMvpView().loadWholeData();
        getMvpView().showLoading();
        //ApiUtils.getJsonPlaceHolderService().getPhotos()
        getRetrofit().create(ApiHelper.class).getPhotos()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Photos>>() {
                    @Override
                    public void accept(@NonNull List<Photos> photos) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().loadPhotos(photos.subList(0, 20));//Just show first 20 images from /photos);
                    }
                });
    }
}
