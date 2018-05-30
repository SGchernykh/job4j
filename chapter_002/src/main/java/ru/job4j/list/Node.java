package ru.job4j.list;

/**
 * Node.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Node<T> {
    T value;
    Node<T> next;

    /**
     * Constructor.
     * @param value Value.
     */
    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    /**
     * Check has cycle.
     * @param first Linked List.
     * @return True if has cycle.
     */
    boolean hasCycle(Node first) {
        Node slow = first;
        Node fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}