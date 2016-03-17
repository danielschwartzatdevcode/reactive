package com.devcode.reactive.accumulate;

import com.devcode.reactive.observable.MyObservables;
import org.junit.Test;
import rx.Observable;

import static org.junit.Assert.*;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class AccumulatePascalsTriangleTest {


    @Test
    public void shouldAccumulateLinesOfPascalsTriangleAsSums() {
        //System.out.println(PascalsTriangle.PASCAL);
        String[] lines = PascalsTriangle.PASCAL.split("\n");
        Observable<String> accumulatingObservable = AccumulatorObservables
                .createAccumulatingObservable(lines,
                        (String accu, String head) -> String.format("%s,%s", accu, PascalsTriangle.sumRow(head)));

        MyObservables.observableApiInfo(accumulatingObservable,"accumulation over pascal rows");
    }


    @Test
    public void shouldAccumulateEulerSolutions(){

    }


}