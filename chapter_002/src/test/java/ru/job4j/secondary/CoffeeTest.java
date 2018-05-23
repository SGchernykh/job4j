package ru.job4j.secondary;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Coffee Machine Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeTest {
    @Test
    public void whenInCoffeeMachineIntroduce50ForCoffee35Result105() {
        Coffee coffee = new Coffee();
        assertThat(coffee.changes(50, 35), is(new int[]{10, 5}));
    }

    @Test
    public void whenInCoffeeMachineIntroduce100ForCoffee35Result1010101010105() {
        Coffee coffee = new Coffee();
        assertThat(coffee.changes(100, 35), is(new int[]{10, 10, 10, 10, 10, 10, 5}));
    }
}
