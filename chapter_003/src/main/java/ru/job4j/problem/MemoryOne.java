package ru.job4j.problem;

public class MemoryOne implements Runnable {
    private Account account;

    public MemoryOne(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        this.account.setIter(this.account.getIter() + 1);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
