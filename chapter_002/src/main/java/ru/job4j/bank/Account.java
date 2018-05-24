package ru.job4j.bank;
/**
 * @author Sergey Chernykh(chernykh.sergey95@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String  getRequisites() {
        return requisites;
    }

    @Override
    public String toString() {
        return String.format("Account{value=%s, requisites=%s}", value, requisites);
    }
}