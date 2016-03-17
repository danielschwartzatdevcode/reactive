package se.schw.rtz.rxtest;

import org.junit.Assert;
import org.junit.Test;

public class PrimesTest
{

   @Test
   public void testIsPrime()
   {
      Assert.assertTrue(Primes.isPrime(101L));
      Assert.assertFalse(Primes.isPrime(4L));
      Assert.assertTrue(Primes.isPrime(2L));
   }

   @Test
   public void test101()
   {
      Assert.assertTrue(Primes.isPrime(101L));
   }
}
