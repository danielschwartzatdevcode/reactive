package com.devcode.reactive.observable;

import org.junit.Test;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by christopheryamba on 27/02/16.
 */
public class MyObservablesTest {

    @Test
    public void justCanJustHandle9Args() {

        Action1<Integer> oneArgActionPrint = System.out::print;
        Action1<Throwable> oneArgActionPrintln = System.out::println;
        Action0 zeroArgActionPrintln = System.out::println;

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribe(oneArgActionPrint,
                        oneArgActionPrintln,
                        zeroArgActionPrintln);
    }

    class Company {
        public Company(String name, String place) {
            this.name = name;
            this.place = place;
        }

        public final String name;
        public final String place;
    }

    @Test
    public void justObservableSourceCanBeMapped() {
        Observable.just(new Company("DevCode", "Stockholm"))
                .map(company -> String.format("%s %s", company.name, company.place))
                .subscribe(System.out::println);
    }

    @Test
    public void shouldPrintInfoAboutIntervalObservable() throws InterruptedException {
        MyObservables.observableApiInfo(Observable.interval(3L, TimeUnit.SECONDS), "Interval Observable");
        Thread.sleep(4000L);
    }


    @Test
    public void shouldPrintInfoAboutTimerObservable() throws InterruptedException {
        MyObservables.observableApiInfo(Observable.timer(2L, TimeUnit.SECONDS), "Timer Observable");
        Thread.sleep(3000L);
    }

    @Test
    public void shouldPrintInfoAboutErrorObservable() {
        MyObservables.observableApiInfo(Observable
                .error(new Exception("Ooooh nooooo ERROR! blue pill!")), "Error Observable");
    }
}