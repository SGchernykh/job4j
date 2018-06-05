package ru.job4j.storage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenTransferUserOneToUserTwo() {
        UserStorage stoge = new UserStorage();
        stoge.add(new User(1, 100));
        stoge.add(new User(2, 200));
        stoge.transfer(1, 2, 50);
        assertThat(stoge.getAmount(2), is(250));
        assertThat(stoge.getAmount(1), is(50));
    }

}