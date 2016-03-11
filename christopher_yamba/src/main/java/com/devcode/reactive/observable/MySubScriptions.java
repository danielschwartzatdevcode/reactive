package com.devcode.reactive.observable;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by christopheryamba on 24/02/16.
 */
public class MySubScriptions {

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

    public static void observe() {
        Observable<Character> observable = Observable.just('s');
        observable.subscribe(
                System.out::print,
                System.out::println,
                System.out::println);
    }

    public static <T> Subscription subscriptionStub(Observable<T> observable) {
        return observable.subscribe(new Subscriber<T>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(T t) {

            }
        });
    }


}
