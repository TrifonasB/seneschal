package com.main.seneschal.domain;

import com.main.seneschal.util.SimpleCalendar;

import java.util.ArrayList;

public class ShoppingList {
    private int id;
    private String name;
    private SimpleCalendar creationDate;
    private ArrayList<ListProduct> spList;


    public ShoppingList() {
        spList = new ArrayList<>();
    }

    public ShoppingList(String name, SimpleCalendar creationDate, ArrayList<ListProduct> spList) {
        this.name = name;
        this.creationDate = creationDate;
        this.spList = spList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){ this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(SimpleCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<ListProduct> getSpList() {
        return spList;
    }

    public void setSpList(ArrayList<ListProduct> spList) {
        this.spList = spList;
    }


    public boolean isActive(){
        for (ListProduct lp : spList) {
            if (!lp.isBought()) return true;
        }
        return false;
    }

    public void addProduct(ListProduct product){
        spList.add(product);
    }

    public void removeProduct(ListProduct listProduct){
        spList.remove(listProduct);
    }

    public void removeBoughtQuantity(BoughtProduct bp){
        Product p = bp.getProduct();
        int index = indexInList(p);

        if(index!=-1)
            spList.get(index).removeQuantity(bp.getQuantity());
    }

    public int indexInList (Product product) {
        for (int i = 0; i < spList.size(); i++) {
            if (spList.get(i).getProduct().equals(product)) return i;
        }
        return -1;
    }



}
