package ru.job4j.bank;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<User, List<Account>> bank = new HashMap<>();
    private Map<String, User> dataP = new HashMap<>();
    private Map<String, Account> dataR = new HashMap<>();

    /**
     * Method add Users in bank systems.
     * @param user The user you add to the Bank.
     */
    public void addUser(User user) {
        List<Account> account = new ArrayList<>();
        this.bank.put(user, account);
        this.dataP.put(user.getPassport(), user);
    }

    /**
     * Method deleted Users from bank systems.
     * @param user The user who was deleted from Bank.
     */
    public void deleteUser(User user) {
        this.bank.remove(user);
        this.dataP.remove(user.getPassport());
    }

    /**
     * The method of adding an account to the banking system.
     * @param passport Passport the Users.
     * @param account Account the Users.
     */
    public void addAccountToUser(String passport, Account account) {
        List<Account> accounts = getUserAccounts(passport);
        accounts.add(account);
        this.bank.put(this.dataP.get(passport), accounts);
        this.dataR.put(account.getRequisites(), account);
    }

    /**
     * The method of deleted an account from banking system.
     * @param passport Passport the Users.
     * @param account Account the Users.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        List<Account> accounts = getUserAccounts(passport);
        accounts.remove(account);
        this.bank.put(this.dataP.get(passport), accounts);
    }

    /**
     * The method a list of accounts.
     * @param passport Passport the Users.
     * @return list of accounts.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = this.bank.get(this.dataP.get(passport));
        return accounts;
    }

    /**
     * The method transfer money between accounts.
     * @param srcPassport The user's passport, which  transfers.
     * @param srcRequisite Account details where write off.
     * @param destPassport The user's passport, to whom transfer.
     * @param dstRequisite Account details to transfer.
     * @param amount Amount transfer.
     * @return True is valid, False is the no funds in account.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = true;
        List<Account> accountsOfSrcUser = this.bank.get(this.dataP.get(srcPassport));
        List<Account> accountsOfDstUser = this.bank.get(this.dataP.get(destPassport));
        if (accountsOfSrcUser.contains(this.dataR.get(srcRequisite)) && this.dataR.get(srcRequisite).getValue() >= amount) {
            double newAccountValue = this.dataR.get(dstRequisite).getValue() + amount;
            String newAccountRequisites = this.dataR.get(dstRequisite).getRequisites();
            accountsOfDstUser.set(accountsOfDstUser.indexOf(this.dataR.get(dstRequisite)), new Account(newAccountValue, newAccountRequisites));
            newAccountValue = this.dataR.get(srcRequisite).getValue() - amount;
            newAccountRequisites = this.dataR.get(srcRequisite).getRequisites();
            accountsOfSrcUser.set(accountsOfSrcUser.indexOf(this.dataR.get(srcRequisite)), new Account(newAccountValue, newAccountRequisites));
        } else {
            result = false;
        }
        return result;
    }
}
