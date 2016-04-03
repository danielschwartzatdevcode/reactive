package se.schw.rtz.rxtest;

import rx.Observable;
import rx.functions.Action1;

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

   @SuppressWarnings("unchecked")
   public static <T> T next(Observable<T> observable)
   {
      Object[] result = new Object[1];

      observable.subscribe(new Action1<T>()
      {

         @Override
         public void call(T t)
         {
            result[0] = t;
         }
      });

      return (T) result[0];
   }

}
