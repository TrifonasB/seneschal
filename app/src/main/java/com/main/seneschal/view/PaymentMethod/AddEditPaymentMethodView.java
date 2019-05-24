package com.main.seneschal.view.PaymentMethod;

import com.main.seneschal.domain.CardType;
import com.main.seneschal.domain.PaymentMethod;
import com.main.seneschal.util.Money;
import com.main.seneschal.util.SimpleCalendar;

public interface AddEditPaymentMethodView {

    String getPaymentMethodType();
    int getBalance();

    String getCardNo();
    SimpleCalendar getExpirationDate();
    CardType getCardType();

    Integer getAttachedPaymentMethodID();

    void setPaymentMethodType(String value);

    void setBalance(int value);

    void setCardNo(String value);

    void setExpirationDate(SimpleCalendar value);

    void setCardType(CardType value);

    void successfullyFinishActivity(String message);

    void showErrorMessage(String title, String message);

}
