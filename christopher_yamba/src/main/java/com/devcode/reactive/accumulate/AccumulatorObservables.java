package com.devcode.reactive.accumulate;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class AccumulatorObservables {

    public static <T> Observable<T> createAccumulatingObservable(T[] source, Func2<T,T,T> accumulator){
        return Observable.from(source).scan(accumulator);
    }
}
