package ru.job4j.bombermen;

/**
 * Board.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final int row;
    private final int column;
    private final ReentrantLock[][] board;

    /**
     * Constructor.
     * @param row Row.
     * @param column Column.
     */
    public Board(final int row, final int column) {
        this.row = row;
        this.column = column;
        this.board = new ReentrantLock[row][column];
        fillBoard();
    }

    /**
     * Fill Board.
     */
    private void fillBoard() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     *
     * @param cell
     * @return
     */
    public boolean addCell(final Cell cell) {
        boolean result = false;
        if (checkCell(cell)) {
            this.board[cell.getX()][cell.getY()].lock();
            result = true;
        }
        return result;
    }

    /**
     * Check valid point.
     * @param cell point to check
     * @return true if point valid.
     */
    public boolean checkCell(final Cell cell) {
        boolean flag = false;
        if (cell.getX() >= 0 && cell.getY() >= 0 && cell.getX() < this.row && cell.getY() < this.column) {
            flag = true;
        }
        return flag;
    }

    /**
     * Move.
     * @param source Source.
     * @param dist Dist.
     * @return True if success move.
     */
    public boolean moveCell(Cell source, Cell dist) {
        boolean result = false;
        if (addCell(source)) {
            try {
                if (dist != null && this.board[dist.getX()][dist.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        result = true;
                    } finally {
                        this.board[source.getX()][source.getY()].unlock();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }
}