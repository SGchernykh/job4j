package ru.job4j.secondary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Coffee Machine Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BracketCheckerTest {
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
    @Test
    public void whenBracketCheckerValid() {
        BracketChecker bracket = new BracketChecker("a{b([c])d}e");
        bracket.check();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Valid: [ at 1 | ] at 6 ")
                                .append(System.lineSeparator())
                                .append("Valid: ( at 3 | ) at 7 ")
                                .append(System.lineSeparator())
                                .append("Valid: { at 4 | } at 9 ")
                                .append(System.lineSeparator())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
    @Test
    public void whenBracketCheckerError() {
        BracketChecker bracket = new BracketChecker("a{b(}[c])d}e");
        bracket.check();
        assertThat(new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .toString()
                )
        );
    }

}
