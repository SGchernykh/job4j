
package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {

    @Test
    public void whenPyramidHeight2() {
        Paint paint = new Paint();
        String rst = paint.pyramid(2);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add(" ^ ")
                                .add("^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramidHeight3() {
        Paint paint = new Paint();
        String rst = paint.pyramid(3);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("  ^  ")
                                .add(" ^^^ ")
                                .add("^^^^^")
                                .toString()
                )
        );
    }
}