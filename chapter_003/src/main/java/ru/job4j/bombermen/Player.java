package ru.job4j.bombermen;

/**
 * Player.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Player {
    private Cell player;
    private final Board board;

    /**
     * Constructor.
     * @param player Cell player.
     * @param board Board.
     */
    public Player(final Cell player, final Board board) {
        this.player = player;
        this.board = board;
    }

    /**
     * Move player.
     * @param dest Dest
     * @return True success.
     */
    public boolean movePlayer(Cell dest) {
        boolean result = false;
        if (this.board.moveCell(this.player, dest)) {
            result = true;
            this.player = dest;
        }
        return result;
    }
}