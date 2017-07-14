package ir.hosseinabbasi.holidaypirates.di;

/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


@Scope /*@Scope is used to specify the scope in which a dependency object persists. If a class getting dependencies, have members injected with classes annotated with a scope, then each instance of that class asking for dependencies will get its own set of member variables.*/
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
