package com.devcode.euler368;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by christopheryamba on 04/03/16.
 */
public class DigitsTest {

    @Test
    public void shouldReturnTrueWhen4ConsecutiveDigitsin1233335() {
        int i = 1288888883;
        int n = 7;
        Assert.assertTrue(Digits.hasNConsecutive(i, n));
    }

    @Test
    public void shouldReturnBIgDecimalValueWhenHas3ConsecutiveDigitsInDenominator() {
        Optional<BigDecimal> number = Digits.fractionWithNonConsecutive(122245788, 3);
        Assert.assertTrue(number.isPresent());
        System.out.println(number.get());
    }

    @Test
    public void should() {
        int total = 1200;

        Object[] objects = IntStream.range(1, total)
                .mapToObj(denom -> Digits.fractionWithNonConsecutive(denom, 3))
                .filter(fraction -> fraction.isPresent())
                .map(i -> i.get())
                .toArray();


        System.out.println(
                String.format("total : %s count : %s", total, Arrays.deepToString(objects))
        );
    }


    @Test
    public void should2() {
        print(String.format("%s", Digits.integersWith3ConsecutiveDigits(999)));
    }

    @Test
    public void shouldReturn20AllNumbersInListSinceContains3Consecutives() {
        String result = IntStream.of(111, 222, 333, 444, 555, 666, 777, 888, 999,
                1000, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1119)
                .mapToObj(i -> Digits.countConsecutive(i, 3))
                .filter(i -> i.isPresent())
                .map(i -> i.get())
                .map(i -> i.toString())
                .collect(Collectors.joining(","));
        Assert.assertEquals(20, result.split(",").length);
        print(result);
    }

    private void print(Object o) {
        System.out.println(o);
    }


    @Test
    public void shouldReturnTrueWhen2Consecutive() {
        boolean hasConsecutive = Digits.hasNConsecutive(32211, 3);
        Assert.assertFalse(hasConsecutive);
        System.out.println(hasConsecutive);
    }

    @Test
    public void shouldReturnFalseWhenCount3And11223() {
        boolean hasConsecutive = Digits.hasNConsecutive(11223, 3);
        Assert.assertFalse(hasConsecutive);
        System.out.println(hasConsecutive);
    }

    @Test //TODO refactor with RxJava
    public void shouldReturnConvergentSeries() {
        long start = System.currentTimeMillis();
        while (true) {
            double elapsedTimeInSeconds = nbrOfSeconds(start, System.currentTimeMillis());
            String kempner = Digits.kempner(start);
            if (elapsedTimeInSeconds > 60d) {
                Assert.assertTrue(false);
                break;
            } else {
                Assert.assertTrue(true);
            }
        }
    }

    private double nbrOfSeconds(long start, long end) {
        double diff = (double) end - (double) start;
        return diff / 1000d;
    }
}