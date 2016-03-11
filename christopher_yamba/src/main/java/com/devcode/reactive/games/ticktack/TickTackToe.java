package com.devcode.reactive.games.ticktack;

/**
 * @author Christopher Yamba
 */
public class TickTackToe  {


    public static void main(String[] args) {
        Board board = Board.of(10);
        board.update(new Integer[]{5, 4}, 3833);
        board.update(new Integer[]{0, 3}, 333);
        System.out.println(board);
    }
}
