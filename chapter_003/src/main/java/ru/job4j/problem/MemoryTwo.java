package ru.job4j.problem;

public class MemoryTwo implements Runnable {
    private Account account;

    public MemoryTwo(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        if (this.account.getIter() % 2 == 0) {
            System.out.println("Thread2 = " + this.account.getIter());
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
