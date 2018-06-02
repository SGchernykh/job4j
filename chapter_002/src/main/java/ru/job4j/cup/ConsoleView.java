package ru.job4j.cup;
/**
 * Tree.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Map;

public class ConsoleView {

    /**
     * Displays all books in the console.
     *
     * @param sell sell.
     * @param buy  buy.
     */
    public void display(Map<Double, Order> buy, Map<Double, Order> sell) {
        StringBuilder sb = new StringBuilder();
        System.out.println("BUY   PRICE    SELL     STOCK");
        for (Order order : sell.values()) {
            sb.append(String.format("\t %7s %5s    %6s\n", order.getPrice(), order.getVolume(), order.getStockID()));
        }
        for (Order order : buy.values()) {
            sb.append(String.format("%1s %9s   %13s\n", order.getVolume(), order.getPrice(), order.getStockID()));
        }
        System.out.println(sb);
    }
}