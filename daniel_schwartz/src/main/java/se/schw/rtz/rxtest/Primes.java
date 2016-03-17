package se.schw.rtz.rxtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import rx.Observable;
import rx.Subscription;

public class Primes
{
   /**
    * contains all primes from 0 to limit
    */
   private static ArrayList<Long> primesBelowLimit = new ArrayList<>(Arrays.asList(2L, 3L));

   /**
    * contains some primes from limit to limit^2.
    */
   private static Set<Long> primesAboveLimit = new HashSet<Long>();

   public static boolean isPrime(long n)
   {
      long limit = getLimit();
      if (n <= limit)
      {
         return primesBelowLimit.contains(n);
      }
      else
      {
         if (limit < n && n <= limit * limit)
         {
            return isPrimeWithinLimits(n);
         }

         else // if ((limit ^ 2) < n)
         {
            long newLimit = Math.round(Math.ceil(Math.sqrt(n)));
            findPrimesUpToLimit(newLimit);
            return isPrime(n);
         }
      }
   }

   private static boolean isPrimeWithinLimits(long n)
   {
      if (primesAboveLimit.contains(n)) return true;

      Observable<Integer> obsevableOfOne = Observable.from(primesBelowLimit).filter(t -> n % t == 0).count();

      Integer[] result =
      { 0 };
      obsevableOfOne.subscribe(t -> result[0] = t);

      boolean nIsPrime = result[0] == 0;

      if (nIsPrime) primesAboveLimit.add(n);

      return nIsPrime;
   }

   public static long getLimit()
   {
      return primesBelowLimit.get(primesBelowLimit.size() - 1);
   }

   private static void findPrimesUpToLimit(long newLimit)
   {
      for (long i = getLimit() + 1; i <= newLimit; i++)
      {
         long limit = getLimit();
         if ((limit * limit) <= i) throw new RuntimeException("unexpected");

         if (isPrimeWithinLimits(i))
         {
            primesBelowLimit.add(i);
            primesAboveLimit.remove(i);
         }
      }
   }

}
