package com.devcode.reactive.games.ticktack.protocol;

/**
 * @author Christopher Yamba
 */
public class Message {

    public final String message;
    public final int winner;

    private Message(String message, int winner) {
        this.message = message;
        this.winner = winner;
    }

    public static Message of(String message, int winner) {
        return new Message(message, winner);
    }

    @Override
    public String toString() {
        return String.format(
                "message : %s, winner : %s",message,winner
        );
    }
}
