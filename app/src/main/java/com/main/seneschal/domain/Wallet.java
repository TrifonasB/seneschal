package com.main.seneschal.domain;

import com.main.seneschal.util.Money;

public class Wallet extends PaymentMethod {

    public Wallet() {
    }

    public Wallet(Money balance) {
        this.balance = balance;
    }

}
