package ir.hosseinabbasi.holidaypirates.data;

/**
 * Created by Dr.jacky on 2017/07/13.
 * It is an interface that is implemented by the AppDataManager. It contains methods, exposed for all the data handling operations. Ideally, it delegates the services provided by all the Helper classes.
 * So DataManager interface extends DbHelper and ApiHelper interfaces.
 */

import io.reactivex.Observable;
import ir.hosseinabbasi.holidaypirates.data.db.DbHelper;
import ir.hosseinabbasi.holidaypirates.data.network.ApiHelper;

public interface DataManager extends DbHelper, ApiHelper {
    //Observable<Boolean> fillDatabasePosts(); //To Insert Posts To Local DB
}
