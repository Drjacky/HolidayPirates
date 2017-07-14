package ir.hosseinabbasi.holidaypirates.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Dr.jacky on 2017/07/13.
 */

public interface SchedulerProvider {
    Scheduler ui();
    Scheduler computation();
    Scheduler io();
}
