package ru.job4j.bombermen;

/**
 * StartBombermen.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartBombermen {
    private final int countMonster;
    private final int row;
    private final int column;
    private final Board board;
    private Player player;

    /**
     * Constructor.
     * @param countMonster Count Monsters.
     * @param row Row Board.
     * @param column Column Board.
     */
    public StartBombermen(int countMonster, int row, int column) {
        this.countMonster = countMonster;
        this.row = row;
        this.column = column;
        this.board = new Board(row, column);
        for (int count = 0; count < countMonster; count++) {
            new Thread(new Monster(board, row, column)).start();
        }
    }

    /**
     * Add Player.
     * @param cell Starting positionю
     */
    public void addPlayer(Cell cell) {
        this.player = new Player(cell, board);
    }

    /**
     * Move player.
     * @param dest Dest.
     * @return True success.
     */
    public boolean movePlayer(Cell dest) {
        boolean result = false;
        if (player.movePlayer(dest)) {
            result = true;
        }
        return result;
    }
}
