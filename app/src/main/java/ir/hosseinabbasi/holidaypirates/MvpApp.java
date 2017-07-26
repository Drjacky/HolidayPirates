package ir.hosseinabbasi.holidaypirates;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import javax.inject.Inject;

import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.data.network.ApiEndPoint;
import ir.hosseinabbasi.holidaypirates.di.component.ApplicationComponent;
import ir.hosseinabbasi.holidaypirates.di.component.DaggerApplicationComponent;
import ir.hosseinabbasi.holidaypirates.di.module.ApplicationModule;
import ir.hosseinabbasi.holidaypirates.utils.AppLogger;


/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */

public class MvpApp extends Application {

    /*This class gets DataManager through DI*/
    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        /*DaggerApplicationComponent is the generated class by the Dagger, implementing the ApplicationComponent. We provide the ApplicationModule class that is used to construct the dependencies.*/
        mApplicationComponent = DaggerApplicationComponent.builder()
                /*List of modules that are part of ApplicationComponent, need to be created HERE too*/ /* $ */
                .applicationModule(new ApplicationModule(this)).build();

        /*We have also called the inject method of applicationComponent and passed the instance of the MvpApp class. This is done to use it for providing the DataManager.*/
        mApplicationComponent.inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

    }

    /*ApplicationComponent instance is retained so as to access all the classes that are available in the dependency graph and is express for access.*/
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
