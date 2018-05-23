package ru.job4j.secondary;
/**
 * Coffee Machine
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Coffee {
    /**
     * Какие купюры есть для выдачи.
     */
    private int[] coins = {10, 5, 2, 1};

    /**
     * Метод оплаты кофе и получения сдачи.
     * @param value купюра.
     * @param price цена кофе.
     * @return сдача.
     */
    int[] changes(int value, int price) {
        int[] result = new int[this.coins.length];
        int residue = value - price;
        int interim = residue;
        int iter = 0;
        int count = 0;
        for (int coin = 0; coin < this.coins.length; coin++) {
            if (residue % this.coins[coin] != 0) {
                interim /= this.coins[coin];
                result[coin] = interim;
                residue -= this.coins[coin] * result[coin];
                count += interim;
            } else {
                result[coin] = residue / this.coins[coin];
                count += result[coin];
                break;
            }
        }
        int[] amount = new int[count];
        for (int coin = 0; coin < this.coins.length; coin++) {
            if (iter == count) {
                break;
            }
            for (int index = 0; index < result[coin]; index++) {
                amount[iter] = this.coins[coin];
                iter++;
            }
        }
        return amount;
    }
}
