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
        for (int i = 0; i < 3; i++) {
            if ((table[i][0].hasMarkX() == value && table[i][1].hasMarkX() == value && table[i][2].hasMarkX() == value) || (table[0][i].hasMarkX() == value && table[1][i].hasMarkX() == value && table[2][i].hasMarkX() == value)) {
                return true;
            }
        }
        if ((table[0][0].hasMarkX() == value && table[1][1].hasMarkX() == value && table[2][2].hasMarkX() == value) || (table[2][0].hasMarkX() == value && table[1][1].hasMarkX() == value && table[0][2].hasMarkX() == value)) {
            return true;
        }
        return false;
    }

    public boolean isWinnerO() {
        boolean value = true;
        for (int i = 0; i < 3; i++) {
            if ((table[i][0].hasMarkO() == value && table[i][1].hasMarkO() == value && table[i][2].hasMarkO() == value) || (table[0][i].hasMarkO() == value && table[1][i].hasMarkO() == value && table[2][i].hasMarkO() == value)) {
                return true;
            }
        }
        if ((table[0][0].hasMarkO() == value && table[1][1].hasMarkO() == value && table[2][2].hasMarkO() == value) || (table[2][0].hasMarkO() == value && table[1][1].hasMarkO() == value && table[0][2].hasMarkO() == value)) {
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
