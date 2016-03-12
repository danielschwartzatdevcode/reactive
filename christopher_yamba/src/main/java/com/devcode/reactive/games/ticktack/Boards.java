package com.devcode.reactive.games.ticktack;

import java.util.stream.Stream;

/**
 * @author Christopher Yamba
 */
public class Boards {

    public static int findMaxDigitLength(Integer[]... boardAsRows) {
        return Stream.of(boardAsRows)
                .flatMap(row -> Stream.of(row))
                .mapToInt(i -> i.toString().length())
                .max()
                .getAsInt();
    }
}
