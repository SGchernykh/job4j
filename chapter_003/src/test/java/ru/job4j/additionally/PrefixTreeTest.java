package ru.job4j.additionally;
/**
 * Prefix Tree Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PrefixTreeTest {
    /**
     * In file "mama papa fediama mary"
     */
    @Test
    public void whenInFileFourMath() {
        PrefixTree tree = new PrefixTree();
        for (String line : tree.loadFile("C:/projects/qu.txt")) {
            tree.put(line);
        }
        Set<Integer> example = new HashSet<>(Arrays.asList(0, 18, 2, 15));
        assertThat(tree.getIndexes4Word("ma"), is(example));
    }

    @Test
    public void whenInFileFourMathEqualsIndexOf() {
        Set<Integer> example = new HashSet<>();
        PrefixTree tree = new PrefixTree();
        String test = "mama papa fediama mary";
        tree.put(test);
        System.out.println();
        example.add(test.indexOf("ry"));
        assertThat(tree.getIndexes4Word("ry"), is(example));
    }

}