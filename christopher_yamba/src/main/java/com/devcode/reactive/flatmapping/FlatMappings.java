package com.devcode.reactive.flatmapping;

import rx.functions.Func2;

import java.math.BigDecimal;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class FlatMappings {

    public static final int[] FIBONACCI = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89};

    public static final int DECIMAL_PRECISION = 10;

    public static Func2<Integer, Integer, String> get2Combination() {
        return (x, y) -> String.format("{%s,%s}", x, y);
    }


    public static Func2<Integer, Integer, String> getFibonacciFunction() {
        return (x, y) -> String.format("%s/%s -> %s", FIBONACCI[x], FIBONACCI[y], fiboDivision(x, y));
    }

    private static BigDecimal fiboDivision(long x, long y) {
        return BigDecimal.valueOf(x).divide(BigDecimal.valueOf(y), DECIMAL_PRECISION, BigDecimal.ROUND_UP);
    }

}
