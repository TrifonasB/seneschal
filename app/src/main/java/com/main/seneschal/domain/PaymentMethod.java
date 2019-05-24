package com.main.seneschal.domain;

import com.main.seneschal.util.Money;

import java.math.BigDecimal;

public abstract class PaymentMethod {

    protected Money balance;
    protected int id;

    public boolean isActive (){
        return balance.getAmount().compareTo(BigDecimal.ZERO) > 0;
    }

    public void makePayment (Money payment){ balance = balance.minus(payment); }

    public Money checkBalance (Money propablePayment){
        return balance.minus(propablePayment);
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
