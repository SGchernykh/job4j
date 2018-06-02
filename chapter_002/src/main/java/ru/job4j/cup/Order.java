package ru.job4j.cup;
/**
 * Order.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Order {
    private int orderID;
    private String stockID;
    private String operationTorg;
    private String transactionStock;
    private double price;
    private int volume;

    /**
     * Constructor.
     * @param orderID ID order.
     * @param stockID ID stock.
     * @param operationTorg Transaction bid/ask.
     * @param transactionStock Operation add/delete.
     * @param volume Volume.
     * @param price Price.
     */
    public Order(int orderID, String stockID, String operationTorg, String transactionStock, int volume, double price) {
        this.orderID = orderID;
        this.stockID = stockID;
        this.operationTorg = operationTorg;
        this.transactionStock = transactionStock;
        this.volume = volume;
        this.price = price;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getStockID() {
        return stockID;
    }

    public String getOperationTorg() {
        return operationTorg;
    }

    public String getTransactionStock() {
        return transactionStock;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
