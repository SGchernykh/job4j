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
    private ConsoleView consoleView;

    /**
     * Constructor.
     */
    public StockCup() {
        this.consoleView = new ConsoleView();
    }

    /**
     * Gets orders from map and distributes them to books.
     * @param map map.
     */
    public void distributesOrders(final Map<Integer, Order> map) {
        Queue<Integer> colector = new LinkedList<>();
        for (Order order : map.values()) {
            String book = order.getStockID();
            Book tmp = bookMap.get(book);
            if (tmp == null) {
                tmp = new Book();
                bookMap.put(book, tmp);
                if ("delete".equals(order.getTransactionStock())) {
                    this.distributesDeleteOrders(order, map, colector);
                    break;
                }
                if ("BID".equals(order.getOperationTorg())) {
                    this.mergerOrder(order, tmp.getBuy());
                } else {
                    this.mergerOrder(order, tmp.getSell());
                }
            } else {
                if ("delete".equals(order.getTransactionStock())) {
                    this.distributesDeleteOrders(order, map, colector);
                    break;
                }
                if ("BID".equals(order.getOperationTorg())) {
                    this.transaction(order, tmp.getSell(), tmp.getBuy(),  colector);
                } else {
                    this.transaction(order, tmp.getBuy(), tmp.getSell(), colector);
                }
            }
        }
        for (Integer key : colector) {
            map.remove(key);
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
     * @param collector The collector are out of date order.
     */
    private void transaction(Order order, Map<Double, Order> mapA, Map<Double, Order> mapB, Queue<Integer> collector) {
        int key;
        boolean result;
        if (mapA.keySet().isEmpty()) {
            this.mergerOrder(order, mapB);
        }
        for (Double pric : mapA.keySet()) {
            if (order.getOperationTorg().equals("BID")) {
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
                    collector.add(key);
                    this.mergerOrder(order, mapB);
                } else {
                    if (sum < 0) {
                        mapA.get(pric).setVolume(Math.abs(sum));
                        break;
                    } else {
                        key = mapA.get(pric).getOrderID();
                        mapA.remove(pric);
                        order.setVolume(sum);
                        collector.add(key);
                        collector.add(order.getOrderID());
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
    private void distributesDeleteOrders(Order orders,  Map<Integer, Order> map, Queue<Integer> collector) {
        int key = 0;
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
        collector.add(key);
    }
}