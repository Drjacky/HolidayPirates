package ir.hosseinabbasi.holidaypirates.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Dr.jacky on 2017/07/13.
 *
 */

@Qualifier /* Is used to qualify the dependency.
 * @Qualifier is used to distinguish between objects of the same type but with different instances.
 * An alternative to @Qualifier is @Named annotation provided by Dagger2. @Named itself is annotated with @Qualifier. With @Named we have to provide string identifier for similar class objects and this identifier is used to map the dependency of a class.
 * So we can replace @ActivityContext with something like @Named("activity_context") every where.
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
