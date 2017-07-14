package ir.hosseinabbasi.holidaypirates.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.hosseinabbasi.holidaypirates.data.AppDataManager;
import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.data.db.AppDbHelper;
import ir.hosseinabbasi.holidaypirates.data.db.DbHelper;
import ir.hosseinabbasi.holidaypirates.data.network.ApiHelper;
import ir.hosseinabbasi.holidaypirates.data.network.AppApiHelper;
import ir.hosseinabbasi.holidaypirates.di.ApplicationContext;
import ir.hosseinabbasi.holidaypirates.di.DatabaseInfo;
import ir.hosseinabbasi.holidaypirates.utils.AppConstants;

/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext /*Or @Named("application_context")*/ /* $$$ */
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return AppConstants.DB_VERSION;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
        /* return new AppDbHelper;  //if we need to make a class singleton and we provide it with   NEW   keyword, then annotate the method that provides it in the MODULE with @Singleton instead of putting it on the CLASS.*/
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }
}
