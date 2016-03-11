package com.devcode.euler368;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by christopheryamba on 04/03/16.
 */
public class Digits {

    public static Optional<BigDecimal> fractionWithNonConsecutive(Integer denom, int n) {
        if (hasNConsecutive(denom, n)) {
            BigDecimal fraction = BigDecimal.ONE.divide(BigDecimal.valueOf(denom), 13, BigDecimal.ROUND_HALF_UP);
            return Optional.of(fraction);
        }
        return Optional.empty();
    }

    public static Optional<BigDecimal> fractionWithoutNonConsecutive(Integer denom, int n) {
        if (!hasNConsecutive(denom, n)) {
            BigDecimal fraction = BigDecimal.ONE.divide(BigDecimal.valueOf(denom), 13, BigDecimal.ROUND_HALF_UP);
            return Optional.of(fraction);
        }
        return Optional.empty();
    }

    public static Integer[] toIntDigits(Integer i) {
        String s = i.toString();
        Integer[] digits = new Integer[s.length()];
        for (int j = 0; j < s.length(); j++) {
            Integer digit = Integer.valueOf(s.substring(j, j + 1));
            digits[j] = digit;
        }
        return digits;
    }

    public static String[] toStringDigits(Integer i) {
        String numberStr = i.toString();
        int length = numberStr.length();
        return IntStream.range(0, length).mapToObj(j -> numberStr.substring(j, j + 1))
                .toArray(x -> new String[length]);
    }

    /**
     * @param number           the number to be verified
     * @param nbrOfConsecutive number of consecutive digits to verify
     * @return true if the integer number has nbrOfConsecutive or more consecutive digits
     */
    public static boolean hasNConsecutive(int number, int nbrOfConsecutive) {
        Integer[] ints = toIntDigits(number);
        int count = 1;
        for (int j = 1; j < ints.length; j++) {
            if (ints[j - 1] == ints[j]) {
                count++;
                if (count == nbrOfConsecutive) {
                    return true;
                }
            } else {
                count = 1;
            }
        }
        return false;
    }


    public static Optional<Integer> countConsecutive(int number, int consecutive) {
        Integer[] ints = toIntDigits(number);
        int count = 1;
        for (int i = 1; i < ints.length; i++) {
            if (ints[i - 1] == ints[i]) {
                count++;
                if (count == consecutive) {
                    return Optional.of(number);
                }
            } else {
                count = 1;
            }

        }
        return Optional.empty();
    }

    public static List<Integer> integersWith3ConsecutiveDigits(int endInclusive) {
        return IntStream.rangeClosed(1, endInclusive)
                .filter(i -> hasNConsecutive(i, 3))
                .mapToObj(i -> i)
                .collect(Collectors.toList());
    }

    public static String kempner(long startTime) {
        BigDecimal sum = BigDecimal.ONE;
        int nbrOfConvergentIterations = 0;
        Integer denom = 1;
        while (true) {
            BigDecimal sumBeforeAdd = sum;
            Optional<BigDecimal> aOptional = fractionWithoutNonConsecutive(denom, 3);
            Optional<BigDecimal> bOptional = fractionWithoutNonConsecutive(denom + 1, 2);
            sum = sum.add(extract(aOptional).add(extract(bOptional)));
            if (decimals(sumBeforeAdd).equals(decimals(sum))) {
                System.out.println("seems to be converging");
                nbrOfConvergentIterations++;
                if (nbrOfConvergentIterations == 3) {
                    return sum.toString().split(".")[1].substring(0, 9);
                }
            }
            if (System.currentTimeMillis() - startTime > 60 * 1000) {
                System.out.println(String.format("%s Late!",sum));
                sum.toString().split(".")[1].substring(0, 9);
            }
        }
    }

    private static String decimals(BigDecimal number) {
        String[] d = number.toString().split("\\.");
        String decimals = d.length >= 2 ? d[1] : "";
        return decimals.length() > 0 ?
                decimals.substring(0, decimals.length())
                : IntStream.range(0, decimals.length())
                .mapToObj(i -> "0")
                .collect(Collectors.joining());
    }

    private static String decimals(BigDecimal number, int nbrOfDecimals) {
        String decimals = number.toString().split("\\.")[1];
        return decimals.substring(0, nbrOfDecimals);
    }

    private static BigDecimal extract(Optional<BigDecimal> decimalOptional) {
        return decimalOptional.isPresent() ? decimalOptional.get() : BigDecimal.ZERO;
    }
}
