package se.schw.rtz.rxtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import rx.Observable;

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
      else if (limit < n && n <= limit * limit)
      {
         return isPrimeWithinLimits(n);
      }
      else // if ((limit ^ 2) < n)
      {
         if (isNotPrimeBasedOnKnownPrimes(n))
         {
            return false;
         }
         long newLimit = Math.round(Math.ceil(Math.sqrt(n)));
         findPrimesUpToLimit(newLimit);
         return isPrime(n);
      }
   }

   private static boolean isNotPrimeBasedOnKnownPrimes(long n)
   {
      Observable<Integer> countFactors = Observable.from(primesBelowLimit).concatWith(Observable.from(primesAboveLimit))
         .filter(t -> n % t == 0).count();

      boolean nIsMaybePrime = Util.next(countFactors) == 0;

      // if nIsMaybePrime is true then it means that none of the known primes divide n. But we do not have all primes,
      // so we cant be sure.
      // if nIsMaybePrime is false then it means that some of the known primes divide n. Then for sure n is not prime.

      return !nIsMaybePrime;
   }

   private static boolean isPrimeWithinLimits(long n)
   {
      if (primesAboveLimit.contains(n)) return true;

      Observable<Integer> countFactors = Observable.from(primesBelowLimit).filter(t -> n % t == 0).count();

      Integer r = Util.next(countFactors);

      boolean nIsPrime = r == 0;

      if (nIsPrime)
      {
         primesAboveLimit.add(n);
      }

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
