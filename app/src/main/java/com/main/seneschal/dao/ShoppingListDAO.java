package com.main.seneschal.dao;

import com.main.seneschal.domain.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListDAO {

    protected static List<ShoppingList> entities = new ArrayList<ShoppingList>();

    public void save (ShoppingList entity){
        entities.add(entity);
    }

    public void delete (ShoppingList entity){
        entities.remove(entity);
    }
}
