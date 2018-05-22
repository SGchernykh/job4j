package ru.job4j.chess;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {
    private Board board = new Board();
    @Before
    public void boardArray() {
        this.board.add(new Elephant(new Cell(1, 1)));
    }

    @Test
    public void whenOnTheRoadAnotherFigureGetFalse() {
        this.board.add(new Elephant(new Cell(6, 6)));
        assertThat(this.board.move(new Cell(1, 1), new Cell(7, 7)), is(false));
    }

    @Test
    public void whenSelectingCellWithoutShapeGetFalse() {
        this.board.add(new Elephant(new Cell(6, 6)));
        assertThat(this.board.move(new Cell(1, 5), new Cell(7, 7)), is(false));
    }

    @Test
    public void whenTheSelectedCellIsOccupiedByAnotherFigureGetFalse() {
        this.board.add(new Elephant(new Cell(6, 6)));
        assertThat(this.board.move(new Cell(1, 1), new Cell(6, 6)), is(false));
    }

    @Test
    public void whenTheSelectedFigureCannotBeMovedToCellGetFalse() {
        this.board.add(new Elephant(new Cell(6, 6)));
        assertThat(this.board.move(new Cell(1, 1), new Cell(4, 6)), is(false));
    }

    @Test
    public void whenTheSelectedFigureBeMovedToCellGetTrue() {
        this.board.add(new Elephant(new Cell(6, 6)));
        assertThat(this.board.move(new Cell(1, 1), new Cell(5, 5)), is(true));
    }
}
