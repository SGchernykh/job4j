package ru.job4j.problem;

public class MemoryFour implements Runnable {
    private Account account;

    public MemoryFour(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        this.account.setIter(this.account.getIter() + 1);
        System.out.println(this.account.getIter() + " thread 2");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
