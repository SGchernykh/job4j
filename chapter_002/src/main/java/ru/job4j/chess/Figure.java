package ru.job4j.chess;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class Figure {
    final Cell position;

    public Figure(Cell cell) {
        this.position = cell;

    }

    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    abstract Figure copy(Cell dest);

    public Cell getPosition() {
        return position;
    }
}
