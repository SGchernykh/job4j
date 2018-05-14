package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CalculatorTest {

    @Test
    public void whenAddOnePlusOneThenTw() {
        Calculator calc = new Calculator();
        calc.add(1D,1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result,is(expected));
    }

    @Test
    public void whenSubtract4From3Then1() {
        Calculator calc = new Calculator();
        calc.substract(4,3);
        double result = calc.getResult();
        double expected = 1;
        assertThat(result,is(expected));
    }

    @Test
    public void whenMultiple2By4Then8() {
        Calculator calc = new Calculator();
        calc.multiple(2,4);
        double result = calc.getResult();
        double expected = 8;
        assertThat(result,is(expected));
    }

    @Test
    public void whenDiv4On4Then1() {
        Calculator calc = new Calculator();
        calc.div(4,4);
        double result = calc.getResult();
        double expected = 1;
        assertThat(result,is(expected));
    }
}
