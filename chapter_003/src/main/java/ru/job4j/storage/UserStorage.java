package ru.job4j.storage;
/**
 * Count.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private HashMap<Integer, User> map = new HashMap<>();



    public boolean add(User user) {
        synchronized (this) {
            map.put(user.getId(), user);
            return map.containsKey(user.getId());
        }
    }

    public boolean update(User user) {
        synchronized (this) {
           this.map.put(user.getId(), user);
           return this.map.get(user.getId()).getAmount() == user.getAmount();
        }
    }

    public boolean delete(User user) {
        synchronized (this) {
            this.map.remove(user.getId());
            return !map.containsKey(user.getId());
        }
    }

    public void transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            this.map.get(fromId).setAmount(this.map.get(fromId).getAmount() - amount);
            this.map.get(toId).setAmount(this.map.get(toId).getAmount() + amount);
        }
    }

    public Integer getAmount(int idUser) {
        synchronized (this) {
            return this.map.get(idUser).getAmount();
        }
    }
}
