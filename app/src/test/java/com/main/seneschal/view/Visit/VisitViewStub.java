package com.main.seneschal.view.Visit;

import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.domain.PaymentMethod;
import com.main.seneschal.domain.Product;
import com.main.seneschal.domain.ShoppingList;
import com.main.seneschal.domain.Store;
import com.main.seneschal.util.Money;
import com.main.seneschal.util.Quantity;

import java.util.ArrayList;
import java.util.List;

public class VisitViewStub implements VisitView {
    private String finishMessage;
    private Store visitStore;
    private PaymentMethod visitPaymentMethod;
    private List<ShoppingList> visitLists;
    private List<ListProduct> productsToBuy;
    private Product attachedProductToBuy;
    private Quantity quantityToBuy;
    private Money priceToBuy;

    private VisitPresenter presenter;
    public void setPresenter(VisitPresenter presenter) { this.presenter = presenter;}

    public VisitViewStub(){
        finishMessage = "";
        visitStore = null;
        visitPaymentMethod = null;
        visitLists = new ArrayList<>();
        attachedProductToBuy = null;
    }

    public String getFinishMessage() {
        return finishMessage;
    }

    public Store getVisitStore() {
        return visitStore;
    }

    @Override
    public PaymentMethod getVisitPaymentMethod() {
        return visitPaymentMethod;
    }

    @Override
    public List<ListProduct> getProductsToBuy() {
        return productsToBuy;
    }

    public List<ShoppingList> getVisitLists() {
        return visitLists;
    }

    @Override
    public Product getAttachedProductToBuy() {
        return attachedProductToBuy;
    }

    @Override
    public Quantity getQuantityToBuy() {
        return quantityToBuy;
    }

    @Override
    public Money getPriceToBuy() {
        return priceToBuy;
    }

    public void setAttachedProductToBuy(Product product){
        attachedProductToBuy = product;
    }

    public void successfullyFinishActivity(String message){finishMessage = message;}

    public void showStoreSelectionView(){}

    public void showStoreSelectionResult(Store store){
        visitStore = store;
    }

    @Override
    public void showPaymentMethodSelectionView() { }

    @Override
    public void showPaymentMethodSelectionResult(PaymentMethod paymentMethod) {
        visitPaymentMethod = paymentMethod;
    }

    @Override
    public void showVisitListSelectionView(){ }

    @Override
    public void showVisitListSelectionResult(List<ShoppingList> visitLists, List<ListProduct> productsToBuy) {
        this.visitLists = visitLists;
        this.productsToBuy = productsToBuy;
    }
}
