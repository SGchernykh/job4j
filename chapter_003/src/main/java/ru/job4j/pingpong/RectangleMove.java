package ru.job4j.pingpong;

/**
 * RectangleMove.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private int wall = 0;

    /**
     * Constructor.
     * @param rect Rectangle.
     * @param limitX Max Size X in Window.
     */
    public RectangleMove(Rectangle rect, int limitX) {
        this.limitX = limitX;
        this.rect = rect;
    }

    @Override
    public void run() {
        while (true) {
            if (this.rect.getX() == limitX) {
                wall = 1;
            }
            if (this.rect.getX() == 0) {
                wall = 0;
            }
            if (wall == 0) {
                this.rect.setX(this.rect.getX() + 1);
            } else {
                this.rect.setX(this.rect.getX() - 1);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
