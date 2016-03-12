package com.devcode.reactive.time;

import com.devcode.reactive.games.ticktack.protocol.Message;
import com.devcode.reactive.observable.MyObservables;
import org.junit.Test;
import rx.Observable;
import rx.schedulers.Timestamped;

import java.util.Arrays;
import java.util.List;


/**
 * Created by christopheryamba on 12/03/16.
 */
public class TimeObservablesTest {

    @Test
    public void shouldCreateTimeStampedObservable() {
        List<Message> messages = Arrays.asList(Message.of("First price!", 1), Message.of("Second prince!", 2));
        Observable<Timestamped<Message>> timeStampedObservable = TimeObservables.createTimeStampedObservable(messages);
        MyObservables.observableApiInfo(timeStampedObservable,"TimeStamp Observables");
    }
}