package ru.job4j.cup;
/**
 * Book.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Book {

    /**
     * Comparator by ascending.
     */
    private final Comparator<Double> ascending = new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o2.compareTo(o1);
        }
    };

    /**
     * Comparator by descending.
     */
    private final Comparator<Double> descending = new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    };

    /**
     * Buy orders.
     */
    private Map<Double, Order> buy = new TreeMap<>(descending);

    /**
     * Sell orders.
     */
    private Map<Double, Order> sell = new TreeMap<>(ascending);

    public Map<Double, Order> getBuy() {
        return this.buy;
    }

    public Map<Double, Order> getSell() {
        return this.sell;
    }
}