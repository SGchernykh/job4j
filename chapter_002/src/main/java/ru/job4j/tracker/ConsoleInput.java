package ru.job4j.tracker;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.Scanner;

public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
