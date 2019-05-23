package com.main.seneschal.view.Product.AddEditProduct;

import com.main.seneschal.domain.ProductCategory;
import com.main.seneschal.domain.ProductSubCategory;

public interface AddEditProductView {

    String getName();
    ProductCategory getCategory();
    ProductSubCategory getSubCategory();

    String getAttachedProductName();


    void setName(String value);

    void setCategory(ProductCategory value);

    void setSubCategory(ProductSubCategory value);

    void successfullyFinishActivity(String message);

    void showErrorMessage(String title, String message);
}
