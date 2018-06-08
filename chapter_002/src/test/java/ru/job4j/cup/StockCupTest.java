package ru.job4j.cup;
/**
 * Stock Cup Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StockCupTest {
    PrintStream stdout = System.out;
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void createTracer() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    StockCup cup = new StockCup();
    Map<Integer, Order> mapOrder = new HashMap<>();

    @Test
    public void whenAddThreeOrder() {
        Order order = new Order(1, "Gaz", "ASK", "add", 10, 1600);
        Order order1 = new Order(2, "Gaz", "BID", "add", 10, 1500);
        Order order2 = new Order(3, "Vaz", "BID", "add", 10, 1500);
        mapOrder.put(order1.getOrderID(), order1);
        mapOrder.put(order.getOrderID(), order);
        mapOrder.put(order2.getOrderID(), order2);
        cup.distributesOrders(order);
        cup.distributesOrders(order1);
        cup.distributesOrders(order2);

        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append((String.format("\t %7s %5s    %6s\n", order.getPrice(), order.getVolume(), order.getStockID())))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append((String.format("\t %7s %5s    %6s\n", order.getPrice(), order.getVolume(), order.getStockID())))
                .append(String.format("%1s %9s   %13s\n", order1.getVolume(), order1.getPrice(), order1.getStockID()))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append((String.format("\t %7s %5s    %6s\n", order.getPrice(), order.getVolume(), order.getStockID())))
                .append(String.format("%1s %9s   %13s\n", order1.getVolume(), order1.getPrice(), order1.getStockID()))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", order2.getVolume(), order2.getPrice(), order2.getStockID()))
                .append(System.lineSeparator())
                .toString()
        ));
    }

    @Test
    public void whenAddTwoOrderWhitEqualsPrice() {
        Order order = new Order(1, "Gaz", "BID", "add", 10, 1500);
        Order order1 = new Order(2, "Gaz", "BID", "add", 10, 1500);
        cup.distributesOrders(order);
        cup.distributesOrders(order1);
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", order.getVolume(), order1.getPrice(), order1.getStockID()))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", order.getVolume() + order1.getVolume(), order1.getPrice(), order1.getStockID()))
                .append(System.lineSeparator())
                .toString()
        ));
    }

    @Test
    public void whenAddTwoOrderBuyOrderExcludesAskOrder() {
        Order order = new Order(1, "Gaz", "ASK", "add", 10, 1500);
        Order order1 = new Order(2, "Gaz", "BID", "add", 11, 1500);
        cup.distributesOrders(order);
        cup.distributesOrders(order1);
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append((String.format("\t %7s %5s    %6s\n", order.getPrice(), order.getVolume(), order.getStockID())))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", 1, order1.getPrice(), order1.getStockID()))
                .append(System.lineSeparator())
                .toString()
        ));
    }

    @Test
    public void whenAddTwoOrderAskOrderExcludesBuyOrder() {
        Order order = new Order(1, "Gaz", "BID", "add", 10, 1500);
        Order order1 = new Order(2, "Gaz", "ASK", "add", 11, 1500);

        cup.distributesOrders(order);
        cup.distributesOrders(order1);
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", 10, order1.getPrice(), order1.getStockID()))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append((String.format("\t %7s %5s    %6s\n", order.getPrice(), 1, order.getStockID())))
                .append(System.lineSeparator())
                .toString()
        ));
    }

    @Test
    public void whenAddTwoOrderAndDeletedOrder() {
        Order order = new Order(1, "Gaz", "BID", "add", 10, 1500);
        Order order1 = new Order(2, "Naz", "ASK", "add", 11, 1500);
        Order order2 = new Order(2, "Naz", "ASK", "delete", 11, 1500);
        cup.distributesOrders(order);
        cup.distributesOrders(order1);
        cup.distributesOrders(order2);
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", order.getVolume(), order.getPrice(), order.getStockID()))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", order.getVolume(), order.getPrice(), order.getStockID()))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append((String.format("\t %7s %5s    %6s\n", order1.getPrice(), order1.getVolume(), order1.getStockID())))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(String.format("%1s %9s   %13s\n", order.getVolume(), order.getPrice(), order.getStockID()))
                .append(System.lineSeparator())
                .append("BUY   PRICE    SELL     STOCK")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()
        ));

    }
}