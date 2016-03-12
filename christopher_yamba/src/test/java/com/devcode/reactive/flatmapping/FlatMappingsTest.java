package com.devcode.reactive.flatmapping;

import com.devcode.reactive.observable.MyObservables;
import org.junit.Test;
import rx.Observable;

import static org.junit.Assert.*;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class FlatMappingsTest {

    @Test
    public void shouldCreateAlternativeToTuples() {
        Observable<String> flatMapPositions = Observable.just(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                .flatMap(
                        v -> {
                            int start = v;
                            return Observable.range(v, 10 - start)
                                    .filter(x -> x != start);
                        },
                        (x, y) -> String.format("{%s,%s}", x, y));

        MyObservables.observableApiInfo(flatMapPositions, "flatMap Observable with tuple operations");

    }

    @Test
    public void shouldFilterInRange() {
        int start = 0;
        MyObservables.observableApiInfo(Observable.range(start, 10).filter(v -> v != start), "filter  in range");
    }
}