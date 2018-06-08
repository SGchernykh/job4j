package ru.job4j.cup;
/**
 * Stock Cup.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.*;

public class StockCup {
    private Map<String, Book> bookMap = new HashMap<>();
    private ConsoleView consoleView = new ConsoleView();
    private Map<Integer, Order> map = new HashMap<>();


    /**
     * Gets orders from map and distributes them to books.
     *
     */
    public void distributesOrders(Order order) {
        String book = order.getStockID();
        Book tmp = bookMap.get(book);
        if ("delete".equals(order.getTransactionStock())) {
            this.distributesDeleteOrders(order, map);
        } else {
            if (tmp == null) {
                tmp = new Book();
                bookMap.put(book, tmp);
                if ("BID".equals(order.getOperationTorg())) {
                    this.mergerOrder(order, tmp.getBuy());
                    map.put(order.getOrderID(), order);
                } else {
                    this.mergerOrder(order, tmp.getSell());
                    map.put(order.getOrderID(), order);
                }
            } else {
                if ("BID".equals(order.getOperationTorg())) {
                    this.transaction(order, tmp.getSell(), tmp.getBuy());
                    map.put(order.getOrderID(), order);
                } else {
                    this.transaction(order, tmp.getBuy(), tmp.getSell());
                    map.put(order.getOrderID(), order);
                }
            }
        }

        for (Book books : bookMap.values()) {
            consoleView.display(books.getBuy(), books.getSell());
        }
    }

    /**
     * The transaction for the purchase or sale of.
     * @param order Order.
     * @param mapA Buy or sell map.
     * @param mapB  Sell or buy  map.
     */
    private void transaction(Order order, Map<Double, Order> mapA, Map<Double, Order> mapB) {
        int key;
        boolean result;
        if (mapA.keySet().isEmpty()) {
            this.mergerOrder(order, mapB);
        }
        for (Double pric : mapA.keySet()) {
            if ("BID".equals(order.getOperationTorg())) {
                result = order.getPrice() >= pric;
            } else {
                result = order.getPrice() <= pric;
            }
            if (result) {
                int sum = order.getVolume() - mapA.get(pric).getVolume();
                if (sum > 0) {
                    key = mapA.get(pric).getOrderID();
                    mapA.remove(pric);
                    order.setVolume(sum);
                    map.remove(key);
                    this.mergerOrder(order, mapB);
                } else {
                    if (sum < 0) {
                        mapA.get(pric).setVolume(Math.abs(sum));
                        break;
                    } else {
                        key = mapA.get(pric).getOrderID();
                        mapA.remove(pric);
                        order.setVolume(sum);
                        map.remove(key);
                        map.remove(order.getOrderID());
                        break;
                    }
                }
            } else {
                this.mergerOrder(order, mapB);
            }
        }
    }

    /**
     * Method merger order with equals price.
     * @param order Order.
     * @param map Buy or sell map.
     */
    private void mergerOrder(final Order order, final Map<Double, Order> map) {
        if (map.containsKey(order.getPrice())) {
            Order tmp = new Order(order.getOrderID(),
                    order.getStockID(),
                    order.getOperationTorg(),
                    order.getTransactionStock(),
                    order.getVolume() + map.get(order.getPrice()).getVolume(),
                    order.getPrice());
            map.put(order.getPrice(), tmp);
        } else {
            map.put(order.getPrice(), order);
        }
    }

    /**
     * Deleted order.
     * @param order Order.
     * @param map Buy or sell map.
     */
    private void deleteOrder(final Order order, final Map<Double, Order> map) {
        if (map.containsKey(order.getPrice())) {
            map.remove(order.getPrice());
        }
    }

    /**
     * Deleted order from map Orders.
     * @param orders Order.
     * @param map Map order.
     * @return True if delete order.
     */
    private void distributesDeleteOrders(Order orders,  Map<Integer, Order> map) {
        int key = orders.getOrderID();
        for (Order order : map.values()) {
            String book = order.getStockID();
            Book tmp = bookMap.get(book);
            if (tmp != null) {
                if ("BID".equals(order.getOperationTorg()) && order.getOrderID() == orders.getOrderID()) {
                    this.deleteOrder(orders, tmp.getBuy());
                    key = order.getOrderID();
                } else {
                    this.deleteOrder(orders, tmp.getSell());
                    key = order.getOrderID();
                }
            }
        }
        map.remove(key);
    }

}