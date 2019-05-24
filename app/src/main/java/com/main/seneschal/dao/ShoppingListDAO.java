package com.main.seneschal.dao;

import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.domain.Product;
import com.main.seneschal.domain.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListDAO {

    protected static int nextListID = 0;

    protected static List<ShoppingList> entities = new ArrayList<ShoppingList>();

    public void save (ShoppingList entity){
        entity.setId(nextListID++);
        entities.add(entity);
    }

    public void delete (ShoppingList entity){

        entities.remove(entity);
    }

    public List<ShoppingList> findAll(){
        ArrayList<ShoppingList> result = new ArrayList<ShoppingList>();
        result.addAll(entities);
        return result;
    }

    public ShoppingList find(String name){
        for(ShoppingList shoppingList : entities){
            if(name.compareToIgnoreCase(shoppingList.getName())==0)
                return shoppingList;
        }

        return null;
    }

    public void reset(){ nextListID = 0;}

    /*
    public List<ListProduct> findAllProductsFromList(int id){
        ArrayList<ListProduct> result = new ArrayList<>();

        for(ShoppingList shoppingList : entities){
            if(shoppingList.getId()==id){
                result.addAll(shoppingList.getSpList());
            }
        }

        return result;
    }
    */
}
