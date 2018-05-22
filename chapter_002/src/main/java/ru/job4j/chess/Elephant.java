package ru.job4j.chess;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Elephant extends Figure {


    public Elephant(Cell cell) {
        super(cell);
    }

    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] cells = new Cell[Math.abs(dest.getX() - source.getX())];
        if (Math.abs(dest.getX() - source.getX()) == Math.abs(dest.getY() - source.getX())) {
            for (int index = 0; index < cells.length; index++) {
                cells[index] = new Cell(dest.getX() - index, dest.getY() - index);
            }
        } else {
            throw new ImpossibleMoveException("Impossible Move!");
        }
        return cells;
    }

    @Override
    Figure copy(Cell dest) {
        return new Elephant(dest);
    }
}
