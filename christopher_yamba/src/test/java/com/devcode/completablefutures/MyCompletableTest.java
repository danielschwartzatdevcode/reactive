package com.devcode.completablefutures;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by christopheryamba on 20/03/16.
 */
public class MyCompletableTest {

    @Test
    public void testBestOf() {
        MyCompletable.bestOf(124357654321L, IsPrimeFactory.isPrimeFaster(), IsPrimeFactory.isPrimeSlow());
    }

}