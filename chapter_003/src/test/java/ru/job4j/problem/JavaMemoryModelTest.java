package ru.job4j.problem;
/**
 * Count Test.
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

public class JavaMemoryModelTest {
    private JavaMemoryModel jmm = new JavaMemoryModel();

    @Test
    public void whenRaceCondition() {
        this.jmm.startJMM();
    }

    @Test
    public void whenVisibilityOfSharedObjects() {
     this.jmm.startJmmVisibility();
    }
}