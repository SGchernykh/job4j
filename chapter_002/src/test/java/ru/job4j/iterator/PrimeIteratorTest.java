package ru.job4j.iterator;
/**
 * Test Iterator of prime numbers in array.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PrimeIteratorTest {
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        this.it = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnPrimeNumbersOnly() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(5));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(7));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3571));
        assertThat(this.it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.next(), is(5));
        assertThat(this.it.next(), is(7));
        assertThat(this.it.next(), is(3571));
    }

    @Test
    public void shouldReturnFalseCauseThereIsNoAnyPrimeNumber() {
        this.it = new PrimeIterator(new int[]{4, 6});
        assertThat("should return false, cause there is no any prime number", this.it.hasNext(), is(false));
    }
}