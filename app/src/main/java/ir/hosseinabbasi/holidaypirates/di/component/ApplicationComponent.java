package ir.hosseinabbasi.holidaypirates.di.component;

/**
 * Created by Dr.jacky on 2017/07/13.
 * Links the MvpApp dependency and the ApplicationModule.
 * This class also provides methods that are used to access the dependencies that exist in the dependency graph.
 */

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ir.hosseinabbasi.holidaypirates.MvpApp;
import ir.hosseinabbasi.holidaypirates.data.DataManager;
import ir.hosseinabbasi.holidaypirates.di.ApplicationContext;
import ir.hosseinabbasi.holidaypirates.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)/* $ */
public interface ApplicationComponent {

    /*When the dependencies are provided through field injection i.e. @inject on the member variables, we have to tell the Dagger to scan this class through the implementation of THIS interface.*/
    void inject(MvpApp app);

    @ApplicationContext
    /*Downstream components need these exposed*/
    Context context();

    /*If we wish to have multiple components that do not need to remain in memory all the time (i.e. components that are tied to the lifecycle of an activity or fragment), we can create dependent components or subcomponents*/
    Application application();

    /*Dependent components require the parent component to explicitly list out what dependencies can be injected downstream, while subcomponents do not. For parent components, you would need to expose to the downstream component by specifying the type and a method*/
    DataManager getDataManager();
}
