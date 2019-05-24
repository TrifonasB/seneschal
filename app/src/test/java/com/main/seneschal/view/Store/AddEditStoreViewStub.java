package com.main.seneschal.view.Store;

import com.main.seneschal.domain.Address;
import com.main.seneschal.domain.DaySchedule;
import com.main.seneschal.view.PaymentMethod.AddEditPaymentMethodViewStub;

public class AddEditStoreViewStub implements AddEditStoreView {

    private String name,errorTitle,errorMessage,finishMessage;
    private DaySchedule[] schedule;
    private Address address;
    private Integer attachedStoreID;

    private AddEditStorePresenter presenter;
    public void setPresenter(AddEditStorePresenter presenter) {this.presenter = presenter;}

    public AddEditStoreViewStub(){
        name = errorTitle = errorMessage = finishMessage = "";
        schedule = null;
        address = null;
    }

    public void setAttachedStoreID(Integer value){attachedStoreID=value;}

    @Override
    public String getName() {
        return name;
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

    @Override
    public DaySchedule[] getSchedule() {
        return schedule;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public Integer getAttachedStoreID() {
        return attachedStoreID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSchedule(DaySchedule[] schedule) {
        this.schedule = schedule;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    public void successfullyFinishActivity(String message){finishMessage = message;}

    public void showErrorMessage(String title, String message){
        errorTitle = title;
        errorMessage = message;
    }
}
