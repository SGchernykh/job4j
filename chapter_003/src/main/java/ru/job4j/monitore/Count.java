package ru.job4j.monitore;
/**
 * Count.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public  void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public  int get() {
        synchronized (this) {
            return this.value;
        }
    }
}