package ru.job4j.switcher;

/**
 * Switcher Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

public class SwitcherTest {

    @Test
    public void whenTwoThreadAddOneAndTwo() {
        Switcher switcher = new Switcher();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switcher.getString();
    }
}