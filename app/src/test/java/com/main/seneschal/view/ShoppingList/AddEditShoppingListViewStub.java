package com.main.seneschal.view.ShoppingList;

import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.util.SimpleCalendar;

import java.util.ArrayList;

public class AddEditShoppingListViewStub implements AddEditShoppingListView {
    private String name, errorTitle, errorMessage, finishMessage, attachedProductName;
    private SimpleCalendar creationDate;
    private ArrayList<ListProduct> spList;
    private AddEditShoppingListPresenter presenter;

    public void setPresenter (AddEditShoppingListPresenter presenter){
        this.presenter = presenter;
    }

    public AddEditShoppingListPresenter getPresenter() {
        return presenter;
    }

    public AddEditShoppingListViewStub() {
        name = errorTitle = errorMessage = finishMessage = "";
        creationDate = null;
        spList = null;
    }

    public void setAttachedProductName (String name) {
        attachedProductName = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SimpleCalendar getCreationDate() {
        return creationDate;
    }

    @Override
    public ArrayList<ListProduct> getSpList() {
        return spList;
    }

    @Override
    public String getAttachedListName() {
        return attachedProductName;
    }

    public void setAttachedlistName(String name){
        attachedProductName = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCreationDate(SimpleCalendar creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public void setSpList(ArrayList<ListProduct> spList) {
        this.spList = spList;
    }

    @Override
    public void successfullyFinishActivity(String msg) {
        finishMessage = msg;
    }

    @Override
    public void showErrorMessage(String title, String msg) {
        errorTitle = title;
        errorMessage = msg;
    }

    public String getErrorTitle (){
        return errorTitle;
    }

    public String getErrorMessage () {
        return errorMessage;
    }

    public String getFinishMessage () {
        return finishMessage;
    }

    public String getAttachedProductName() {
        return attachedProductName;
    }
}

