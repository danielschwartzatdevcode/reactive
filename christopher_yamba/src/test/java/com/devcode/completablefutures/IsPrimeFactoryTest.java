package com.devcode.completablefutures;

import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Created by christopheryamba on 20/03/16.
 */
public class IsPrimeFactoryTest {


    @Test
    public void verifyIsPrimeSlow(){
        verify(IsPrimeFactory.isPrimeSlow());
    }

    @Test
    public void verifyIsPrimeFast(){
        verify(IsPrimeFactory.isPrimeFaster());
    }

    private void verify(Predicate<Long> isPrime){
        assertTrue(isPrime.test(2L));
        assertTrue(isPrime.test(5L));
        assertTrue(isPrime.test(11L));
        assertTrue(isPrime.test(23L));
        assertFalse(isPrime.test(24L));
        assertFalse(isPrime.test(4L));
        assertFalse(isPrime.test(48L));
    }
}