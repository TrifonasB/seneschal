package com.main.seneschal.view.PaymentMethod;

import com.main.seneschal.domain.CardType;
import com.main.seneschal.util.SimpleCalendar;

public class AddEditPaymentMethodViewStub implements AddEditPaymentMethodView{

    private String paymentMethodType;

    private String cardNo;
    private String errorTitle;
    private String errorMessage;
    private String finishMessage;
    private SimpleCalendar expires;
    private CardType cardType;
    private Integer attachedPaymentMethodID;
    private int balance;

    private AddEditPaymentMethodPresenter presenter;

    public AddEditPaymentMethodPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(AddEditPaymentMethodPresenter presenter) {
        this.presenter = presenter;
    }

    public AddEditPaymentMethodViewStub(){
        cardNo = errorTitle = errorMessage = finishMessage = "";
        expires = null;
        cardType = null;
    }

    public void setAttachedPaymentMethodID(Integer value){ attachedPaymentMethodID = value;}

    @Override
    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    @Override
    public String getCardNo() {
        return cardNo;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFinishMessage() {
        return finishMessage;
    }

    public SimpleCalendar getExpirationDate() {
        return expires;
    }

    @Override
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public Integer getAttachedPaymentMethodID() {
        return attachedPaymentMethodID;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    @Override
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setExpirationDate(SimpleCalendar expires) {
        this.expires = expires;
    }

    @Override
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void successfullyFinishActivity(String message){finishMessage = message;}

    public void showErrorMessage(String title, String message){
        errorTitle = title;
        errorMessage = message;
    }
}
