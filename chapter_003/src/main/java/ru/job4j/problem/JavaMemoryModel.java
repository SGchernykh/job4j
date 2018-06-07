package ru.job4j.problem;
/**
 * JavaMemoryModel.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class JavaMemoryModel {
    Account account = new Account(0);

    /**
     * Race Condition.
     */
    public void startJMM() {
        for (int index = 0; index < 1500; index++) {
            new Thread(new MemoryOne(account)).start();
            new Thread(new MemoryTwo(account)).start();
        }
    }

    /**
     * Visibility Of Shared Objects.
     */
    public void startJmmVisibility() {
        for (int index = 0; index < 1000; index++) {
            new Thread(new MemoryTree(account)).start();
            new Thread(new MemoryFour(account)).start();

        }
    }
}