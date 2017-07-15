package ir.hosseinabbasi.holidaypirates.ui.detail;

import java.util.List;

import ir.hosseinabbasi.holidaypirates.data.db.model.Comments;
import ir.hosseinabbasi.holidaypirates.di.PerActivity;
import ir.hosseinabbasi.holidaypirates.ui.base.MvpPresenter;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

@PerActivity
public interface DetailMvpPresenter<V extends DetailMvpView> extends MvpPresenter<V> {
    void onViewInitialized();
}
