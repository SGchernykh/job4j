package ru.job4j.loop;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * Board.
     * @param width Width of the board.
     * @param height Height of the board.
     * @return Доска в псевдографике.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int out= 0; out < height; out ++ ) {
            for ( int in = 0; in < width; in ++ ) {
                if ((out + in) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}