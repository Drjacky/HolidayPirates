package ir.hosseinabbasi.holidaypirates.ui.detail;

import android.os.Handler;

import com.androidnetworking.error.ANError;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.data.db.model.Posts;
import ir.hosseinabbasi.holidaypirates.ui.base.BasePresenter;
import ir.hosseinabbasi.holidaypirates.utils.rx.SchedulerProvider;

/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */

public class DetailPresenter<V extends DetailMvpView> extends BasePresenter<V>
        implements DetailMvpPresenter<V> {

    @Inject
    public DetailPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

    }

    @Override
    public void onViewInitialized() {
        getMvpView().loadWholeData();
    }
}
