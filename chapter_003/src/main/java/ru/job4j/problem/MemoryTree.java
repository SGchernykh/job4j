package ru.job4j.problem;

/**
 * MemoryTree.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MemoryTree implements Runnable {
    private Account account;

    /**
     * Constructor.
     * @param account Account.
     */
    public MemoryTree(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        this.account.setIter(this.account.getIter() + 1);
        System.out.println(this.account.getIter() + " thread 1");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}