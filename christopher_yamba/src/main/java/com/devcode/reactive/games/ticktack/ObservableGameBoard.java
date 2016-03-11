package com.devcode.reactive.games.ticktack;

import com.devcode.reactive.games.ticktack.protocol.Message;

import java.util.concurrent.Future;

/**
 * Created by christopheryamba on 11/03/16.
 */
public interface ObservableGameBoard<T extends Message> extends Future<T>{

}
