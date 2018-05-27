package ru.job4j.secondary;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BracketChecker {
    private final char[] beginning = {'{', '[', '(', '<'};
    private final char[] ends = {'}', ']', ')', '>'};
    private String input;

    /**
     * Конструктор.
     *
     * @param in строка для проверки.
     */
    public BracketChecker(String in) {
        input = in;
    }

    /**
     * Метод для проверки вадидности заданной строки
     */
    public void check() {
        boolean result = false;
        StringBuilder valid = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>(this.input.length());
        Deque<Integer> index = new ArrayDeque<>();
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            for (char begin : this.beginning) {
                if (ch == begin) {
                    stack.add(ch);
                    index.add(j);
                }
            }
            for (char end : this.ends) {
                if (ch == end) {
                    if (!stack.isEmpty()) {
                        char chx = stack.pollLast();
                        for (int ind = 0; ind < this.beginning.length; ind++) {
                            result = false;
                            if (ch == this.ends[ind] && chx == this.beginning[ind]) {
                                valid.append(String.format("Valid: %s at %s | %s at %s %n", chx, index.pop(), ch, j));
                                result = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (result) {
            System.out.println(valid);
        }
    }
}
