package com.devcode.reactive.observable;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

public class MyObservables {


    public static <T> Subscription observableApiInfo(Observable<T> observable, String apiInfo) {
        Action1<T> onNext = onNext(apiInfo);
        Action1<Throwable> onError = onError(apiInfo);
        Action0 onCompleted = onCompleted(apiInfo);
        return observable.subscribe(onNext, onError, onCompleted);
    }

    private static Action0 onCompleted(String apiInfo) {
        return () -> System.out.println(String.format("%s completed!", apiInfo));
    }

    private static <T> Action1<T> onNext(String apiInfo) {
        return value -> System.out.println(String.format("apiInfo : %s value : %s", apiInfo, value));
    }

    private static Action1<Throwable> onError(String apiInfo) {
        return e -> {
            System.err.println(String.format("Error. apiInfo : %s", apiInfo));
            System.err.println(e.getMessage());
        };
    }
}
