package com.devcode.reactive.accumulate;

import rx.Observable;

import java.util.stream.Stream;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class PascalsTriangle {

    private static final String NL = "\n";

    public static final String PASCAL
            = "1"+NL
            + "1 1"+NL
            + "1 2 1"+NL
            + "1 3 3 1"+NL
            + "1 4 6 4 1"+NL
            + "1 5 10 10 5 1"+NL
            + "1 6 15 20 15 6 1"+NL
            + "1 7 21 35 35 21 7 1"+NL
            + "1 8 28 56 70 56 28 8 1"+NL
            + "1 9 36 84 126 126 84 36 9 1";

    public static long sumRow(String head) {
        String[] split = head.split("\\s+");
        return Stream.of(split).mapToInt(Integer::valueOf).sum();
    }
}
