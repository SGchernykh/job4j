package ru.job4j.chess;
/**
 * Board.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {
    private Figure[] figures = new Figure[32];
    private int posicion = 0;

    /**
     * Add figure in board.
     * @param figure Figure.
     */
    public void add(Figure figure) {
        if (this.posicion != 32) {
            this.figures[this.posicion] = figure;
            this.posicion++;
        }
    }

    /**
     * Move figure
     * @param source current place figure.
     * @param dest future place figure.
     * @return true if move success or false if failed.
     * @throws ImpossibleMoveException Exception Cannot Move.
     * @throws OccupiedWayException Exception Cell Occupied.
     * @throws FigureNotFoundException Exception Figure Not Found.
     */
    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        try {
            boolean result = false;
            boolean occupied = false;
            boolean not = true;
            boolean move = false;
            for (int index = 0; index < this.posicion; index++) {
                if (source.equals(figures[index].getPosition())) {
                    not = false;
                }
                if (dest.equals(figures[index].getPosition())) {
                    occupied = true;
                    throw new OccupiedWayException("Cell Occupied !");
                }
                if (this.figures[index].getPosition().equals(source)) {
                    for (Cell cell : this.figures[index].way(source, dest)) {
                        for (int figure = 0; figure < this.posicion; figure++) {
                            if (cell.equals(figures[figure].getPosition())) {
                                move = true;
                            }
                        }
                    }
                }
            }
            if (move) {
                throw new ImpossibleMoveException("On the road another figure");
            }
            if (not) {
                throw new FigureNotFoundException("Select a cell with a figure!");
            }
            if (!occupied && !not && !move) {
                for (int index = 0; index < this.posicion; index++) {
                    if (this.figures[index].getPosition().equals(source)) {
                        this.figures[index] = this.figures[index].copy(dest);
                        break;
                    }
                }
                result = true;
            }
            return result;
        } catch (OccupiedWayException owe) {
            System.out.println(owe);
            return false;
        } catch (FigureNotFoundException fnfe) {
            System.out.println(fnfe);
            return false;
        } catch (ImpossibleMoveException ime) {
            System.out.println(ime);
            return false;
        }
    }
}
