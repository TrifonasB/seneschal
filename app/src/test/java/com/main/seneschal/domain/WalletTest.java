package com.main.seneschal.domain;

import com.main.seneschal.util.Money;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WalletTest {

    private Wallet wallet;

    @Before
    public void setUp() throws Exception {
        wallet = new Wallet();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void correctWalletSetup(){
        wallet.setBalance(Money.euros(100));

        Assert.assertEquals(wallet.getBalance(),Money.euros(100));
    }

    @Test
    public void walletAfterPayment(){
        wallet.setBalance(Money.euros(200));
        wallet.makePayment(Money.euros(152));

        Assert.assertEquals(wallet.getBalance(),Money.euros(48));
    }

    @Test
    public void walletAfterChecking(){
        wallet.setBalance(Money.euros(200));
        Money test = wallet.checkBalance(Money.euros(152));

        Assert.assertEquals(test,Money.euros(48));
        Assert.assertEquals(wallet.getBalance(),Money.euros(200));
    }

    @Test
    public void walletCanBeUsed(){
        wallet.setBalance(Money.euros(10));

        Assert.assertTrue(wallet.isActive());
    }

    @Test
    public void walletDisabledAfterBigTransaction(){
        Wallet wallet2 = new Wallet(Money.euros(100));
        wallet2.makePayment(Money.euros(100));

        Assert.assertFalse(wallet2.isActive());
    }
}