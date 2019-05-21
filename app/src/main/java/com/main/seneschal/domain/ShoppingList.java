package com.main.seneschal.domain;

import com.main.seneschal.util.SimpleCalendar;

import java.util.ArrayList;

class ShoppingList {
    protected static int currentListId=0;
    private int id;
    private String name;
    private SimpleCalendar creationDate;
    private ArrayList<ListProduct> spList;


    public ShoppingList() {
        id = currentListId++;
        spList = new ArrayList<>();
    }

    public ShoppingList(String name, SimpleCalendar creationDate) {
        this.name = name;
        this.creationDate = creationDate;
        spList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(){
        id = currentListId++;
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
        int i=0;
        Product p = bp.getProduct();
        while(!p.equals(spList.get(i).getProduct())){
            i++;
        }
        spList.get(i).removeQuantity(bp.getQuantity());
    }

    public boolean contains(Product product){
        if(product!=null){
            for(ListProduct lp : spList){
                if(lp.getProduct().equals(product)) return true;
            }
        }
        return false;
    }



}
