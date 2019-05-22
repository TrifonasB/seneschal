package com.main.seneschal.domain;



import com.main.seneschal.util.Money;
import com.main.seneschal.util.Quantity;
import com.main.seneschal.util.SimpleCalendar;

import java.util.ArrayList;

public class Visit {
    private int id;
    private SimpleCalendar visitDate;
    private Money total;
    private ArrayList<BoughtProduct> bpList;
    private ArrayList<ShoppingList> visitLists;
    private Store visitedStore;
    private PaymentMethod paymentMethod;


    public Visit(SimpleCalendar visitDate, Store visitedStore, PaymentMethod paymentMethod, ArrayList<ShoppingList> visitLists) {
        this.visitDate = visitDate;
        this.bpList = new ArrayList<>();
        this.visitLists = visitLists;
        this.visitedStore = visitedStore;
        this.paymentMethod = paymentMethod;

        total = Money.euros(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public SimpleCalendar getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(SimpleCalendar visitDate) {
        this.visitDate = visitDate;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public ArrayList<BoughtProduct> getBpList() {
        return bpList;
    }

    public void setBpList(ArrayList<BoughtProduct> bpList) {
        this.bpList = bpList;
    }

    public ArrayList<ShoppingList> getVisitLists() {
        return visitLists;
    }

    public void setVisitLists(ArrayList<ShoppingList> visitLists) {
        this.visitLists = visitLists;
    }

    public Store getVisitedStore() {
        return visitedStore;
    }

    public void setVisitedStore(Store visitedStore) {
        this.visitedStore = visitedStore;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public void addList(ShoppingList list){
        if (list == null)
            list = new ShoppingList();
        visitLists.add(list);
    }

    public void removeList (ShoppingList list){
        visitLists.remove(list);
    }

    public void addProduct (BoughtProduct product){
        bpList.add(product);
        total = total.plus(product.getTotalPrice());
    }

    public void removeProduct (BoughtProduct bpListProduct){
        bpList.remove(bpListProduct);
        total = total.minus(bpListProduct.getTotalPrice());
    }

    public void adjustQuantity(BoughtProduct bpListProduct, Quantity newQ){
        total = total.minus(bpListProduct.getTotalPrice());
        bpListProduct.setQuantity(newQ);
        total = total.plus(bpListProduct.getTotalPrice());
    }

    public boolean closeToBudget(Money threshold){
        Money expectedBalance = paymentMethod.checkBalance(total);
        if(expectedBalance.getAmount().compareTo(threshold.getAmount())<=0){
            return true;
        }
        return false;
    }

    public boolean contains(Product product){
        if(product!=null) {
            for (BoughtProduct bp : bpList) {
                if (bp.getProduct().equals(product)) return true;
            }
        }

        return false;
    }
}
