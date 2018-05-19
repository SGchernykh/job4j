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
        boolean value = true;
        for (int row = 0; row < this.table.length; row++) {
            if (table[row][0].hasMarkX() == value && table[row][1].hasMarkX() == value && table[row][2].hasMarkX() == value) {
                return true;
            }
        }
        for (int col = 0; col < this.table.length; col++) {
           if (table[0][col].hasMarkO() == value && table[1][col].hasMarkX() == value && table[2][col].hasMarkX() == value) {
               return true;
           }
        }
        if (table[0][0].hasMarkX() == value && table[1][1].hasMarkX() == value && table[2][2].hasMarkX() == value) {
            return true;
        }
        if (table[2][0].hasMarkX() == value && table[1][1].hasMarkX() == value && table[0][2].hasMarkX() == value) {
            return true;
        }
        return false;
    }


    public boolean isWinnerO() {
        boolean value = true;
        for (int row = 0; row < this.table.length; row++) {
            if (table[row][0].hasMarkO() == value && table[row][1].hasMarkO() == value && table[row][2].hasMarkO() == value) {
                return true;
            }
        }
        for (int col = 0; col < this.table.length; col++) {
            if (table[0][col].hasMarkO() == value && table[1][col].hasMarkO() == value && table[2][col].hasMarkO() == value) {
                return true;
            }
        }
        if (table[0][0].hasMarkO() == value && table[1][1].hasMarkO() == value && table[2][2].hasMarkO() == value) {
            return true;
        }
        if (table[2][0].hasMarkO() == value && table[1][1].hasMarkO() == value && table[0][2].hasMarkO() == value) {
            return true;
        }
        return false;
    }

    public boolean hasGap() {
        boolean value = false;
        for (int out = 0; out < table.length; out++) {
            for (int in = 0; in < table.length; in++) {
                if ((table[out][in].hasMarkX() == value) && (table[out][in].hasMarkO() == value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
