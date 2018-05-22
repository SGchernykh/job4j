package ru.job4j.chess;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Exception move impossible.
     * @param message Error message.
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
