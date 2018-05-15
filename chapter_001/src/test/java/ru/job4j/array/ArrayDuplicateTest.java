package ru.job4j.array;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        ArrayDuplicate array = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] except = {"Привет", "Мир","Супер"};
        String[] result = array.remove(input);
        assertThat(result,is(except) );
    }
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateInt() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        ArrayDuplicate array = new ArrayDuplicate();
        String[] input = {"1", "1", "2", "2", "3", "2", "3", "3"};
        String[] except = {"1", "2","3"};
        String[] result = array.remove(input);
        assertThat(result,is(except) );
    }
}
