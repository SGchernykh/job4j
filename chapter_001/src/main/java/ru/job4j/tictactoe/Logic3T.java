package ru.job4j.tictactoe;
/**
 * Win logic ticTacToe.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean cols, rows, toright, toleft;


        for (int col=0; col<3; col++) {
            cols = true;
            rows = true;
            toright = true;
            toleft = true;
            for (int row=0; row<3; row++) {
                toright &= (table[row][row].hasMarkX() == true);
                toleft &= (table[3-row-1][row].hasMarkX() == true);
                cols &= (table[col][row].hasMarkX() == true);
                rows &= (table[row][col].hasMarkX() == true);
            }
            if (cols || rows || toright || toleft ) return true;
        }
        return false;

    }

    public boolean isWinnerO() {
        boolean cols, rows, toright, toleft;

        for (int col=0; col<3; col++) {
            cols = true;
            rows = true;
            toright = true;
            toleft = true;
            for (int row=0; row<3; row++) {
                toright &= (table[row][row].hasMarkO() == true );
                toleft &= (table[3-row-1][row].hasMarkO() == true);
                cols &= (table[col][row].hasMarkO() == true);
                rows &= (table[row][col].hasMarkO() == true);
            }
            if (cols || rows || toright || toleft ) return true;
        }
        return false;

    }

    public boolean hasGap() {
        for (int out = 0; out < table.length; out++) {
            for (int in = 0; in < table.length; in++) {
                if ((table[out][in].hasMarkX() == false) && (table[out][in].hasMarkO() == false)) return true;
            }
        }
        return false;
    }
}
