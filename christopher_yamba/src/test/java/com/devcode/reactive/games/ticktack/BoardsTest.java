package com.devcode.reactive.games.ticktack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by christopheryamba on 27/02/16.
 */
public class BoardsTest {


    @Test
    public void shouldReturnMaxDigitLengthFromBoard() {
        Integer[][] board = {
                {0, 2, 0, 100, 0},
                {3, 5, 88, 4, 1},
                {1, 2, 3, 4, 5},
                {0, 0, 1, 1, 2},
                {12, 13, 14, 777}
        };
        Assert.assertEquals(3, Boards.findMaxDigitLength(board));
    }
}