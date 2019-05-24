package com.main.seneschal.view.ShoppingList;

import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.util.SimpleCalendar;

import java.util.ArrayList;

public interface AddEditShoppingListView {

    String getName();
    SimpleCalendar getCreationDate();
    ArrayList<ListProduct> getSpList();
    String getAttachedListName();

    void setName (String name);
    void setCreationDate(SimpleCalendar creationDate);
    void setSpList (ArrayList<ListProduct> spList);

    void successfullyFinishActivity (String msg);
    void showErrorMessage (String title, String msg);
}
