package com.devcode.reactive;

import com.devcode.reactive.games.ticktack.Board;
import com.devcode.reactive.games.ticktack.protocol.Message;
import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

/**
 * @author Christopher Yamba
 */
public class ConnectingNPlayersApp {


    public static Observable<Message> tickTackObservable(){
        return ConnectableObservable.from(Board.of(4));
    }


    public static void main(String...args) {
        tickTackObservable().subscribe(
                message -> {
                    System.out.println(message);
                }
        );
    }
}
