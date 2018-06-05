package ru.job4j.problem;

public class JavaMemoryModel {
    Account account = new Account(0);

    public void startJMM() {
        for (int index = 0; index < 1500; index++) {
            new Thread(new MemoryOne(account)).start();
            new Thread(new MemoryTwo(account)).start();
        }
    }

    public void startJmmVisibility() {
        for (int index = 0; index < 1000; index++) {
            new Thread(new MemoryTree(account)).start();
            new Thread(new MemoryFour(account)).start();

        }
    }
}
