package ru.job4j.secondary;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class SortedTest {
    @Test
    public void whenSortedArrayByIncrease() {
        String[] array = {"K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2", "K2", "K2/SK1/SSK1", "K2/SK1/SSK2"};
        String[] expected = {"K1", "K1/SK1", "K1/SK1/SSK1", "K1/SK1/SSK2", "K1/SK2", "K2", "K2/SK1", "K2/SK1/SSK1", "K2/SK1/SSK2"};
        Sorted sorted = new Sorted();
        Set<String> result = new LinkedHashSet<>();
        result.addAll(Arrays.asList(expected));
        assertThat(sorted.sortByIncrease(array), is(result));
    }

    @Test
    public void whenSortedByReduction() {
        String[] array = {"K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2", "K2",  "K2/SK1/SSK1", "K2/SK1/SSK2"};
        String[] expected = {"K2", "K2/SK1", "K2/SK1/SSK2", "K2/SK1/SSK1", "K1", "K1/SK1", "K1/SK1/SSK2", "K1/SK1/SSK1", "K1/SK2"};
        Sorted sorted = new Sorted();
        Set<String> result = new LinkedHashSet<>();
        result.addAll(Arrays.asList(expected));
        assertThat(sorted.sortByReduction(array), is(result));
    }
}
