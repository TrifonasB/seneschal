package com.main.seneschal.dao;

import com.main.seneschal.domain.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    protected static int nextStoreID=0;

    protected static List<Store> entities = new ArrayList<Store>();

    public void save (Store entity){
        entity.setId(nextStoreID++);
        entities.add(entity);
    }

    public void delete (Store entity){
        entities.remove(entity);
    }

    public List<Store> findAll(){
        ArrayList<Store> result = new ArrayList<Store>();
        result.addAll(entities);
        return result;
    }

    public Store find(int id){
        for(Store store: entities){
            if(store.getId()==id)
                return store;
        }

        return null;
    }

    public void reset(){ nextStoreID = 0;}
}