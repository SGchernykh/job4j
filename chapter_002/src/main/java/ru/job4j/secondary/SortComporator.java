package ru.job4j.secondary;

import java.util.Comparator;

public class SortComporator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int len1 = left.length();
        int len2 = right.length();
        int lim = Math.min(len1, len2);
        int iter = 0;
        while (iter < lim) {
            char ch1 = left.charAt(iter);
            char ch2 = right.charAt(iter);
            if (ch1 != ch2) {
                return ch2 - ch1;
            }
            iter++;
        }
        return len1 - len2;
    }
}
