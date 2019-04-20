package com.main.seneschal_app.domain;

import com.main.seneschal_app.util.Money;

import java.math.BigDecimal;

public abstract class PaymentMethod {

    protected Money balance;

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
}
