package com.main.seneschal.view.Visit;

import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.domain.PaymentMethod;
import com.main.seneschal.domain.Product;
import com.main.seneschal.domain.ShoppingList;
import com.main.seneschal.domain.Store;
import com.main.seneschal.util.Money;
import com.main.seneschal.util.Quantity;

import java.util.List;

public interface VisitView {

    Store getVisitStore();
    PaymentMethod getVisitPaymentMethod();
    List<ShoppingList> getVisitLists();
    List<ListProduct> getProductsToBuy();

    Product getAttachedProductToBuy();
    Quantity getQuantityToBuy();
    Money getPriceToBuy();



    void successfullyFinishActivity(String message);

    void showStoreSelectionView();

    void showStoreSelectionResult(Store store);

    void showPaymentMethodSelectionView();

    void showPaymentMethodSelectionResult(PaymentMethod paymentMethod);

    void showVisitListSelectionView();

    void showVisitListSelectionResult(List<ShoppingList> visitLists, List<ListProduct> productsToBuy);



}
