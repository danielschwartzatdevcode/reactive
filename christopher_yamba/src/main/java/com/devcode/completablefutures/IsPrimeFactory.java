package com.devcode.completablefutures;

import java.util.function.Predicate;

/**
 * Created by christopheryamba on 20/03/16.
 */
public class IsPrimeFactory {

    public static Predicate<Long> isPrimeSlow() {
        return n -> {
            if (n <= 1) {
                return false;
            }
            if (n == 2) {
                return true;
            }
            if (n % 2 == 0) {
                return false;
            }
            int divisor = 3;
            while (divisor < n) {
                if (n % divisor == 0) {
                    return false;
                }
                divisor += 1;
            }
            return true;
        };
    }

    public static Predicate<Long> isPrimeFaster() {
        return n -> {
            if (n <= 1) {
                return false;
            }
            if(n==2){
                return true;
            }
            if (n % 2 == 0) {
                return false;
            }
            int divisor = 3;
            while (divisor <= Math.sqrt(n)) {
                if (n % divisor == 0) {
                    return false;
                }
                divisor += 2;
            }
            return true;
        };
    }
}
