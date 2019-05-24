package com.main.seneschal.dao;

import com.main.seneschal.domain.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitDAO {

    protected static int nextVisitID = 0;

    protected static List<Visit> entities = new ArrayList<Visit>();

    public void save (Visit entity){
        entity.setId(nextVisitID++);
        entities.add(entity);
    }

    public void delete (Visit entity){
        entities.remove(entity);
    }

    public List<Visit> findAll(){
        ArrayList<Visit> result = new ArrayList<Visit>();
        result.addAll(entities);
        return result;
    }

    public void reset(){ nextVisitID = 0;}

}