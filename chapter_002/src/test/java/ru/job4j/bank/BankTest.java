package ru.job4j.bank;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BankTest {
    /**
     * Test bank system.
     * @param bank - bank.
     */
    Bank bank = new Bank();
    User genry = new User("Genry", "1234");
    User mary = new User("Mary", "4321");
    Account firstAccount = new Account(500, "1");
    Account secondAccount = new Account(100, "2");

    @Test
    public void whenAddNewUser() {
        bank.addUser(genry);
        List<Account> result = bank.getUserAccounts(genry.getPassport());
        assertThat(result.toString(), is("[]"));
    }

    @Test (expected = java.lang.NullPointerException.class)
    public void whenDeleteUser() {
        bank.addUser(genry);
        bank.deleteUser(genry);
        bank.getUserAccounts(genry.getPassport()).toString();
    }

    @Test
    public  void whenAddAccount() {
        bank.addUser(genry);
        bank.addAccountToUser(genry.getPassport(), firstAccount);
        List<Account> result = bank.getUserAccounts(genry.getPassport());
        assertThat(result.toString(), is("[Account{value=500.0, requisites=1}]"));
    }

    @Test
    public void whenDeleteAccount() {
        bank.addUser(genry);
        bank.addAccountToUser(genry.getPassport(), firstAccount);
        bank.deleteAccountFromUser(genry.getPassport(), firstAccount);
        List<Account> result = bank.getUserAccounts(genry.getPassport());
        assertThat(result.toString(), is("[]"));
    }

    @Test
    public void whenGenryTransferMoneyToMary() {
        bank.addUser(genry);
        bank.addUser(mary);
        bank.addAccountToUser(genry.getPassport(), firstAccount);
        bank.addAccountToUser(mary.getPassport(), secondAccount);
        bank.transferMoney(genry.getPassport(), firstAccount.getRequisites(), mary.getPassport(), secondAccount.getRequisites(), 500);
        List<Account> maryAccount = bank.getUserAccounts(mary.getPassport());
        assertThat(maryAccount.toString(), is("[Account{value=600.0, requisites=2}]"));
        List<Account> genryAccount = bank.getUserAccounts(genry.getPassport());
        assertThat(genryAccount.toString(), is("[Account{value=0.0, requisites=1}]"));
    }

    @Test
    public void whenGenryHasNotMoneyForMary() {
        bank.addUser(genry);
        bank.addUser(mary);
        bank.addAccountToUser(genry.getPassport(), firstAccount);
        bank.addAccountToUser(mary.getPassport(), secondAccount);
        boolean isTransfer = bank.transferMoney(genry.getPassport(), firstAccount.getRequisites(), mary.getPassport(), secondAccount.getRequisites(), 999);
        assertThat(isTransfer, is(false));
    }

}
