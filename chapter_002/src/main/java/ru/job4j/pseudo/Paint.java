package ru.job4j.pseudo;
/**
 * @author s.chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint  {
    /**
     * Draw figure.
     * @param shape Shape figure.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
