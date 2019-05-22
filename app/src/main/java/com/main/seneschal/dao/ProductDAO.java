package com.main.seneschal.dao;

import com.main.seneschal.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    protected static int nextProductID = 0;

    protected static List<Product> entities = new ArrayList<Product>();

    public void save (Product entity){
        entity.setId(nextProductID++);
        entities.add(entity);
    }

    public void delete (Product entity){
        entities.remove(entity);
    }

    public List<Product> findAll(){
        ArrayList<Product> result = new ArrayList<Product>();
        result.addAll(entities);
        return result;
    }

    public Product find(String name){
        for(Product product : entities){
            if(name.compareToIgnoreCase(product.getName())==0)
                return product;
        }

        return null;
    }
}
