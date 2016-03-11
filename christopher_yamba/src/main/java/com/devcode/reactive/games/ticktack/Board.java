package com.devcode.reactive.games.ticktack;

import com.devcode.reactive.games.ticktack.protocol.Message;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Christopher Yamba
 */
public class Board extends AbstractBoardGameSubject {

    public Integer[][] board;

    private Board(Integer[][] board) {
        this.board = board;
    }

    public static Board of(int size) {
        Integer[][] newBoard = new Integer[size][size];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[i].length; j++)
                newBoard[i][j] = -1;
        }
        return new Board(newBoard);
    }

    /**
     * update board with a players value. If value is -1 board coordinate is empty and thus updatable
     *
     * @param xy    XY-coordinate on the board
     * @param value which player
     * @return true if successful. false otherwise
     */
    public boolean update(Integer[] xy, int value) {
        if (board[xy[0]][xy[1]] == -1) {
            board[xy[0]][xy[1]] = value;
            return true;
        }
        return false;
    }

    /**
     * @return -1 if no winner
     */
    protected int checkWin() {
        int win = checkRows();
        if (win != -1) {
            return win;
        }
        win = checkColumns();
        if (win != -1) {
            return win;
        }
        return checkDiagonals();
    }

    protected int checkDiagonals() {
        boolean win = true;
        for (int i = 0; i < board.length - 1; i++) {
            win &= board[i][i] == board[i + 1][i + 1];
            if (!win) {
                return -1;
            }
        }
        if (win && board[0][0] != -1) {
            return board[0][0];
        }
        for (int i = board.length - 1; i > 1; i--) {
            win &= board[i][i] == board[i - 1][i - 1];
            if (!win) {
                return -1;
            }
        }
        if (win && board[board.length - 1][0] != -1) {
            return board[board.length - 1][0];
        }
        return -1;
    }

    protected int checkColumns() {
        for (int j = 0; j < board.length; j++) {
            boolean win = true;
            for (int i = 0; i < board[j].length - 1; i++) {
                win &= board[i][j] == board[i + 1][j];
                if (!win) {
                    return -1;
                }
            }
            if (win && board[j][0] != -1) {
                return board[j][0];
            }
        }
        return -1;
    }

    protected int checkRows() {
        for (int i = 0; i < board.length; i++) {
            boolean win = true;
            for (int j = 0; j < board[i].length - 1; j++) {
                win &= board[i][j] == board[i][j + 1];
                if (!win) {
                    return -1;
                }
            }
            if (win) {
                return board[i][0];
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        int paddingSize = Boards.findMaxDigitLength(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                String entry = String.format(" %s ", board[i][j]);
                sb.append(padding(entry, paddingSize));
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    private String padding(String str, int paddingSize) {
        StringBuilder padded = new StringBuilder();
        padded.append(str);
        while (padded.length() < paddingSize) {
            padded.insert(0, " ").insert(padded.length(), " ");
        }
        return padded.toString();
    }

    @Override
    public Message get() throws InterruptedException, ExecutionException {
        int winnerId = checkWin();
        return Message.of("bar", winnerId);
    }

    @Override
    public Message get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        Runnable r = () -> {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
        int winnerId = checkWin();
        return Message.of("foo", winnerId);
    }
}