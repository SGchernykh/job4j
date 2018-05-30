package ru.job4j.list;
/**
 * Interface for Stack and Queue.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface InterfaceSimple<E> {

     /**
      * Deleted and get element from the structure.
      * @return Element.
      */
     E poll();

     /**
      * Add element in structure.
      * @param value Value.
      */
     void push(E value);
}