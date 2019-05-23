package com.main.seneschal.view.Product;

import com.main.seneschal.domain.ProductCategory;
import com.main.seneschal.domain.ProductSubCategory;
import com.main.seneschal.view.Product.AddEditProduct.AddEditProductPresenter;
import com.main.seneschal.view.Product.AddEditProduct.AddEditProductView;

public class AddEditProductViewStub implements AddEditProductView {
    private String name,errorTitle,errorMessage,finishMessage,attachedProductName;
    private ProductCategory category;
    private ProductSubCategory subCategory;

    private AddEditProductPresenter presenter;

    public void setPresenter(AddEditProductPresenter presenter){this.presenter = presenter;}

    public AddEditProductPresenter getPresenter(){return presenter;}

    public AddEditProductViewStub(){
        name = errorTitle = errorMessage = finishMessage = "";
        category = null;
        subCategory = null;
    }

public void setAttachedProductName (String value) {attachedProductName = value;}

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getFinishMessage() {
        return finishMessage;
    }

    public void setFinishMessage(String finishMessage) {
        this.finishMessage = finishMessage;
    }

    public String getAttachedProductName() {
        return attachedProductName;
    }

    @Override
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public ProductSubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductSubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public void successfullyFinishActivity(String message){ finishMessage = message;}

    public void showErrorMessage(String title, String message){
        errorTitle = title;
        errorMessage = message;
    }
}
