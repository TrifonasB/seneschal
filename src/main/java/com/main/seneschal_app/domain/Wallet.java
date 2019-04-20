package com.main.seneschal_app.domain;

import com.main.seneschal_app.util.Money;

public class Wallet extends PaymentMethod {

    public Wallet() {
    }

    public Wallet(Money balance) {
        this.balance = balance;
    }

}
