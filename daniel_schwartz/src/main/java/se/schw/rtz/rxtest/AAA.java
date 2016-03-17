package se.schw.rtz.rxtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.common.io.Closer;

import rx.Observable;
import se.rtz.tool.util.csv.CsvFactory;
import se.rtz.tool.util.csv.CsvReader;
import se.rtz.tool.util.csv.CsvRecord;

public class AAA
{

   public static void main(String[] args) throws IOException
   {
      String pathname = "./testdata/primeNumbers.csv";
      Closer closer = Closer.create();
      try
      {
         CsvReader reader = closer.register(openCsvReader(pathname));

         Observable<CsvRecord> observable = Observable.from(reader);

         Util.printAnObservable(observable);
         // printAnObservable(observable); // - kan inte skrivas ut igen för att CsvReader inte klarar det...
      }
      finally
      {
         closer.close();
      }
   }

   private static CsvReader openCsvReader(String pathname)
   {
      CsvReader reader = null;
      File file = new File(pathname);
      try
      {
         FileReader fileReader = new FileReader(file);
         reader = CsvFactory.newCsvReader(fileReader);
      }
      catch (FileNotFoundException e)
      {
         throw new RuntimeException(e);
      }
      return reader;
   }

}
