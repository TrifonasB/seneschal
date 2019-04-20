package com.main.seneschal_app.domain;

import com.main.seneschal_app.util.Money;
import com.main.seneschal_app.util.SimpleCalendar;
import com.main.seneschal_app.util.SystemDateStub;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class CardTest {

    private Card card;

    @Before
    public void setUp() throws Exception {
        Calendar now = Calendar.getInstance();


        now.set(Calendar.YEAR, 2019);
        now.set(Calendar.MONTH, 3);

        SystemDateStub.setStub(new SimpleCalendar(now));


        card = new Card();
    }

    @After
    public void tearDown() throws Exception {
        SystemDateStub.reset();
    }

    @Test
    public void correctCardSetup(){
        Calendar exp = Calendar.getInstance();
        exp.set(Calendar.YEAR, 2020);
        exp.set(Calendar.MONTH, 2);

        card.setBalance(Money.euros(30));
        card.setCardNo("024235034534");
        card.setCardType(CardType.CREDIT);
        card.setExpires(new SimpleCalendar(exp));

        Assert.assertEquals(card.getCardNo(),"024235034534");
        Assert.assertEquals(card.getCardType(), CardType.CREDIT);
        Assert.assertEquals(card.getExpires().getMonth(),3);
        Assert.assertEquals(card.getExpires().getYear(), 2020);
        Assert.assertEquals(card.getBalance(),Money.euros(30));
    }


    @Test
    public void cardBalanceInvalid(){
        card.setBalance(Money.euros(20));
        card.makePayment(Money.euros(22));

        Assert.assertFalse(card.isActive());

    }

    @Test
    public void cardExpiredWithMoney(){
        card.setBalance(Money.euros(10));

        Calendar exp = Calendar.getInstance();
        exp.set(Calendar.YEAR, 2017);
        exp.set(Calendar.MONTH, 5);

        card.setExpires(new SimpleCalendar(exp));

        Assert.assertFalse(card.isActive());
    }
}