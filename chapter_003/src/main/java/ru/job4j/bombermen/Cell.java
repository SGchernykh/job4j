package ru.job4j.bombermen;

/**
 * Cell.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Cell {
    private  String player = "";
    private final int x;
    private final int y;

    /**
     * Constructor.
     * @param x x.
     * @param y y.
     */
    public Cell(final int x, final int y, final String name) {
        this.x = x;
        this.y = y;
        this.player = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPlayer() {
        return player;
    }
}