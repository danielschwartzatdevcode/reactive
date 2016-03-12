package com.devcode.reactive.time;


import rx.Observable;
import rx.schedulers.Timestamped;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class TimeObservables {

    public static <T> Observable<Timestamped<T>> createTimeStampedObservable(Iterable<T> source){
        return Observable.from(source).timestamp();
    }
}
