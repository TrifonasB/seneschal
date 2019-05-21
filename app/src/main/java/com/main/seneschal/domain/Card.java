package com.main.seneschal.domain;

import com.main.seneschal.util.SimpleCalendar;
import com.main.seneschal.util.SystemDate;

public class Card extends PaymentMethod {

    private String cardNo;
    private SimpleCalendar expires;
    private CardType cardType;

    public Card(){}

    public Card(String cardNo, SimpleCalendar expires, CardType cardType) {
        this.cardNo = cardNo;
        this.expires = expires;
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public SimpleCalendar getExpires() {
        return expires;
    }

    public void setExpires(SimpleCalendar expires) {
        this.expires = expires;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public boolean isActive(){
        boolean activeBalance = super.isActive();
        return activeBalance && (expires.after(SystemDate.now()));
    }
}
