package ru.job4j.secondary;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BracketChecker {
    private String input;

    /**
     * Конструктор.
     * @param in строка для проверки.
     */
    public BracketChecker(String in) {
        input = in;
    }

    /**
     * Метод для проверки вадидности заданной строки
     */
    public void check() {
        boolean result = true;
        StringBuilder valid = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>(this.input.length());
        Deque<Integer> index = new ArrayDeque<>();
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.add(ch);
                    index.add(j);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!stack.isEmpty()) {
                        char chx = stack.pollLast();
                        if ((ch == '}' && chx == '{') || (ch == ']' && chx == '[') || (ch == ')' && chx == '(')) {
                            valid.append(String.format("Valid: %s at %s | %s at %s %n", chx, index.pop(), ch, j));
                        } else {
                            result = false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (result) {
            System.out.println(valid);
        }
    }
}
