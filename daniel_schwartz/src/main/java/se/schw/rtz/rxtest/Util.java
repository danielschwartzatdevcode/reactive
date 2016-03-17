package se.schw.rtz.rxtest;

import rx.Observable;

public class Util
{

   public static <T> void printAnObservable(Observable<T> observable)
   {
      observable.subscribe( //
         t -> System.out.println(t.toString()), //
         t ->
         {
            System.err.println("oops, there was an error");
            t.printStackTrace(System.err);
         }, //
         () -> System.out.println("---")); //
   }

}
