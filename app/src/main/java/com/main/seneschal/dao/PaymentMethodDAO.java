package com.main.seneschal.dao;

import com.main.seneschal.domain.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDAO {

    protected static int nextPaymentMethodID = 0;

    protected static List<PaymentMethod> entities = new ArrayList<PaymentMethod>();

    public void save (PaymentMethod entity){
        entity.setId(nextPaymentMethodID++);
        entities.add(entity);
    }

    public void delete (PaymentMethod entity){
        entities.remove(entity);
    }

    public List<PaymentMethod> findAll(){
        ArrayList<PaymentMethod> result = new ArrayList<PaymentMethod>();
        result.addAll(entities);
        return result;
    }

    public PaymentMethod find(int id){
        for(PaymentMethod paymentMethod : entities){
            if(paymentMethod.getId() == id)
                return paymentMethod;
        }

        return null;
    }
}