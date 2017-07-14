package ir.hosseinabbasi.holidaypirates.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME) /*Dagger 2 does not rely on the annotation at runtime, keeping the RetentionPolicy at RUNTIME is useful in allowing you to inspect your modules later.*/
public @interface ApplicationContext {
}
