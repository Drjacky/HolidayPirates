package ir.hosseinabbasi.holidaypirates.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Dr.jacky on 2017/07/13.
 * This is implemented by the View (BaseActivity, BaseFragment). It contains methods that are exposed to its Presenter for the communication.
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();
}
