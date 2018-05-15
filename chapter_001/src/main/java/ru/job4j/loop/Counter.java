package ru.job4j.loop;

/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {
    public int add(int start, int finish){
        int result = 0;

        for (int i = start; i <= finish; i++) {

            if (i % 2 == 0 ) result += i;

        }
        return result;
    }
}
