package com.devcode.reactive;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by christopheryamba on 24/02/16.
 */
public class MyObservables {

    public static <T> Subscription follow(List<T> source, Predicate<T>... watchPredicates) {

        Subscription subscription = Observable.from(source).subscribe(
                t -> {
                    boolean found = Stream.of(watchPredicates).allMatch(predicate -> predicate.test(t));
                    if (found) {
                        System.out.println(t.toString());
                    }
                }
        );
        return subscription;

    }
}
