package com.revature.project0.models;

import java.util.Objects;

public class Account {

    private Integer id;
    private Double balance;
    private Double withdraw;
    private Double deposit;

    public Account() {
        super();
    }

    public Account (Integer id, Double balance, Double withdraw, Double deposit) {
        this.id = id;
        this.balance = balance;
        this.withdraw = withdraw;
        this.deposit = deposit;
    }

    public Integer getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id)&&
                Objects.equals(balance, account.balance) &&
                Objects.equals(withdraw, account.withdraw) &&
                Objects.equals(deposit, account.deposit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, withdraw, deposit);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", withdraw=" + withdraw +
                ", deposit=" + deposit +
                '}';
    }
}
