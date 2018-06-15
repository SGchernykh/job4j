package ru.job4j.bombermen;

/**
 * Monster.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Random;

public class Monster implements Runnable {
    private final int row;
    private final int column;
    private volatile Cell monster;
    private final Board board;
    private final Random random;
    private static final int MOVE = 4;

    /**
     * Constructor.
     * @param board Board.
     */
    public Monster( final Board board, final int row, int column) {
        this.row = row;
        this.column = column;
        this.board = board;
        this.random = new Random();
    }

    /**
     * Random Move.
     * @param value Move const.
     * @return new Cell.
     */
    private Cell getMoveCell(final int value) {
        Cell cell = null;
        switch (value) {
            case 0:
                cell = new Cell(this.monster.getX() + 1, this.monster.getY(), this.monster.getPlayer());
                break;
            case 1:
                cell = new Cell(this.monster.getX() - 1, this.monster.getY(), this.monster.getPlayer());
                break;
            case 2:
                cell = new Cell(this.monster.getX(), this.monster.getY() + 1, this.monster.getPlayer());
                break;
            case 3:
                cell = new Cell(this.monster.getX(), this.monster.getY() - 1, this.monster.getPlayer());
                break;
            default:
                break;
        }
        return cell;
    }

    @Override
    public void run() {
        spawnPoint();
        while (!Thread.currentThread().isInterrupted()) {
            Cell temp = getMoveCell(this.random.nextInt(MOVE));
            while (!this.board.checkCell(temp)) {
                temp = getMoveCell(this.random.nextInt(MOVE));
            }
            if (this.board.moveCell(this.monster, temp)) {
                monster = new Cell(temp.getX(), temp.getY(), this.monster.getPlayer());
            }
            try {
                Thread.sleep(this.random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void spawnPoint() {
       Cell temp = new Cell(random.nextInt(this.row), random.nextInt(this.column), "monster");
        while (!this.board.checkCell(temp)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
            Cell cell = this.getMoveCell(this.random.nextInt(MOVE));
            if (this.board.checkCell(cell)) {
                this.monster = temp;
            }
        }
    }

}
