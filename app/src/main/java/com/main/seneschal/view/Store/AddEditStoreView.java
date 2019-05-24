package com.main.seneschal.view.Store;

import com.main.seneschal.domain.Address;
import com.main.seneschal.domain.DaySchedule;

public interface AddEditStoreView {

    String getName();
    DaySchedule[] getSchedule();
    Address getAddress();

    Integer getAttachedStoreID();

    void setName(String value);

    void setSchedule(DaySchedule[] value);

    void setAddress(Address value);

    void successfullyFinishActivity(String message);

    void showErrorMessage(String title, String message);
}
