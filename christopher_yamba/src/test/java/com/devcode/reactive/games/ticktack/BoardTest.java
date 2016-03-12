package com.devcode.reactive.games.ticktack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @author Christopher Yamba
 */
public class BoardTest {

    static final int SIZE = 5;
    Board target;

    @Before
    public void setUp() throws Exception {
        target = Board.of(SIZE);
    }

    @Test
    public void shouldUpdateBoard() {
        Assert.assertTrue(target.update(new Integer[]{1, 1}, 1));
    }

    @Test
    public void shouldUpdateAllBoardCoordinatesWithinRange() {
        for (int i = 0; i < target.board.length; i++) {
            for (int j = 0; j < target.board[i].length; j++) {
                Assert.assertTrue(target.update(new Integer[]{i, j}, 1));
            }
        }
    }


    @Test
    public void shouldNotUpdateWhenTaken() {
        Assert.assertTrue(target.update(new Integer[]{0, 0}, 1));
        Assert.assertFalse(target.update(new Integer[]{0, 0}, 1));
    }

    @Test
    public void shouldReturnNoWinnerWhenNotPlayed() {
        int win = target.checkWin();
        Assert.assertEquals(-1, win);
    }


    @Test
    public void shouldReturnNoWinnerWhenTwoOutOfThreeRow() {
        target.update(new Integer[]{0, 0}, 1);
        target.update(new Integer[]{0, 1}, 1);
        int win = target.checkWin();
        Assert.assertEquals(-1, win);
    }

    @Test
    public void shouldReturnWinWhenFallingLRDiagonal() {
        Integer[][] fallingLRDiagonal = createFallingLRDiagonal(3);
        boolean update = update(1, fallingLRDiagonal);
        Assert.assertTrue(update);
    }

    @Test
    public void shouldReturnWinWhenRisingLRDiagonal() {
        Integer[][] risingDiagonal = createRisingLRDiagonal(SIZE);
        boolean update = update(1, risingDiagonal);
        Assert.assertTrue(update);
        int win = target.checkWin();
        Assert.assertEquals(1, win);

    }

    @Test
    public void shouldReturnWinWhenColumnSet() {
        Assert.assertTrue(IntStream.range(0, SIZE).allMatch(col -> update(1, createColumn(SIZE, col))));
        int win = target.checkWin();
        Assert.assertEquals(1, win);

    }

    @Test
    public void shouldReturnWinWhenRowSet() {
        Assert.assertTrue(IntStream.range(0, SIZE).allMatch(row -> update(1, createRow(SIZE, row))));
        int win = target.checkWin();
        Assert.assertEquals(1, win);
    }

    private boolean update(int value, Integer[][] xy) {
        return Stream.of(xy).allMatch(v -> target.update(v, value));
    }

    private Integer[][] createFallingLRDiagonal(int size) {
        Integer[][] diagonal = new Integer[size][2];
        IntStream.range(0, size).forEachOrdered(i -> diagonal[i] = new Integer[]{i, i});
        return diagonal;
    }

    private Integer[][] createRisingLRDiagonal(int size) {
        Integer[][] diagonal = new Integer[size][2];
        IntStream.range(0, size).forEachOrdered(i -> diagonal[size - i - 1] = new Integer[]{i, i});
        return diagonal;
    }


    private Integer[][] createRow(int size, int r) {
        Integer[][] row = new Integer[size][2];
        for (int i = 0; i < size; i++) {
            row[i] = new Integer[]{r, i};
        }
        return row;
    }

    private Integer[][] createColumn(int size, int c) {
        Integer[][] col = new Integer[size][2];
        for (int i = 0; i < size; i++) {
            col[i] = new Integer[]{i, c};
        }
        return col;
    }

}