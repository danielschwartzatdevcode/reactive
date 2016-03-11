package com.devcode.reactive.games.ticktack;

import com.devcode.reactive.games.ticktack.protocol.Message;

/**
 * Created by christopheryamba on 11/03/16.
 */
public abstract class AbstractBoardGameSubject implements ObservableGameBoard<Message> {

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
