package ru.job4j.lock;

/**
 * Simple Lock.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SimpleLock {
    @GuardedBy("this")
    private boolean isLocked;

    /**
     * lock - проверяет свободен ли лок? Если да - захватывает, иначе блокируется.
     */
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isLocked = true;
    }

    /**
     * unlock - проверяет владеет ли поток локом? Если да то - освобождает.
     */
    public synchronized void unlock() {
        this.isLocked = false;
        notifyAll();
    }
}
