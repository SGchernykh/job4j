package ru.job4j.problem;

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