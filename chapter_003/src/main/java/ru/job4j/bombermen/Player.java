package ru.job4j.bombermen;

/**
 * Player.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Random;

public class Player implements Runnable {
    private volatile Cell player;
    private final Board board;
    private final Random random;
    private static final int MOVE = 4;

    /**
     * Constructor.
     * @param player Cell player.
     * @param board Board.
     */
    public Player(final Cell player, final Board board) {
        this.player = player;
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
                cell = new Cell(this.player.getX() + 1, this.player.getY(), this.player.getPlayer());
                break;
            case 1:
                cell = new Cell(this.player.getX() - 1, this.player.getY(), this.player.getPlayer());
                break;
            case 2:
                cell = new Cell(this.player.getX(), this.player.getY() + 1, this.player.getPlayer());
                break;
            case 3:
                cell = new Cell(this.player.getX(), this.player.getY() - 1, this.player.getPlayer());
                break;
            default:
                break;
        }
        return cell;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Cell temp = getMoveCell(this.random.nextInt(MOVE));
            while (!this.board.checkCell(temp)) {
                temp = getMoveCell(this.random.nextInt(MOVE));
            }
            if (this.board.moveCell(this.player, temp)) {
                player = new Cell(temp.getX(), temp.getY(), this.player.getPlayer());
            }
            try {
                Thread.sleep(this.random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}