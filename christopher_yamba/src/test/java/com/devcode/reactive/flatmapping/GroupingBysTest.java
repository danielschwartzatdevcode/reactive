package com.devcode.reactive.flatmapping;

import com.devcode.reactive.observable.MyObservables;
import org.junit.Test;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by christopheryamba on 12/03/16.
 */
public class GroupingBysTest {

    @Test
    public void shouldReturnGroupingByObservable() {
        Action1<GroupedObservable> reportFrequency = (GroupedObservable observable) ->
                MyObservables.observableApiInfo(observable,"GroupingBy observable "+ observable.getKey() + " count of 'e'");
        Func1 filterOutE = name -> name.toString().replaceAll("[eE]", "").length();
        Func1 showOccurences = name -> name.toString().replaceAll("[eE]", "*");

        GroupingBys.createFrom(Arrays.asList("Christopher", "Daniel", "Daniel", "Daniel", "Eva", "Leonardo"),
                filterOutE,
                showOccurences)
                .subscribe(reportFrequency);
    }

    @Test
    public void shouldReturnGroupingByObservableTernaryStrings() {
        Action1<GroupedObservable> reportFrequency = (GroupedObservable observable) ->
                MyObservables.observableApiInfo(observable, "GroupingBy observable "+observable.getKey() + " count of '1'");
        Func1 filterOutE = name -> name.toString().replaceAll("[^1]", "").length();
        Func1 showOccurences = name -> name.toString().replaceAll("[1]", "*");

        GroupingBys.createFrom(Arrays.asList("0011222", "002211", "0101012", "0000000", "222221", "1111122211"),
                filterOutE,
                showOccurences)
                .subscribe(reportFrequency);
    }

}