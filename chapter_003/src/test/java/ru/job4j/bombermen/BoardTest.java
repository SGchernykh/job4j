package ru.job4j.bombermen;

/**
 * BoardTest.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

public class BoardTest {
    @Test
    public void whenMoveTwoPlayer() {
        Board board = new Board(10, 10);
        Cell player1 = new Cell(2, 1, "Player1");
        Cell player2 = new Cell(5, 4, "Player2");
        Thread play1 = new Thread(new Player(player1, board));
        Thread play2 = new Thread(new Player(player2, board));
        play1.start();
        play2.start();
    }
}