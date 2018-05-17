package ru.job4j.pseudo;
/**
 * @author s.chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TriangleTest {

    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("    +")
                                .append(System.lineSeparator())
                                .append("   + +")
                                .append(System.lineSeparator())
                                .append("  +   +")
                                .append(System.lineSeparator())
                                .append(" +     +")
                                .append(System.lineSeparator())
                                .append("+++++++++")
                                .toString()
                )
        );
    }
}
