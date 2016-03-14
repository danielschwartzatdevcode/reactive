package com.devcode.reactive.flatmapping;

import rx.Observable;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class GroupingBys {

    public static Observable createFrom(Iterable source, Func1 func1, Func1 transformer) {
     return   Observable.from(source).groupBy(func1, transformer);
    }

}
