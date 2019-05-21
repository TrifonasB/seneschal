package com.main.seneschal.domain;

import com.main.seneschal.util.Quantity;

import java.math.BigDecimal;

public class ListProduct {

    private Product product;
    private Quantity quantity;

    public ListProduct() {}

    public ListProduct(Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public boolean isBought(){
        return quantity.getAmount().compareTo(BigDecimal.ZERO) <= 0;
    }

    public void removeQuantity (Quantity quantity){
        this.quantity = this.quantity.minus(quantity);

        if(this.quantity.getAmount().compareTo(BigDecimal.ZERO)<0)
            this.quantity = new Quantity(BigDecimal.ZERO,this.quantity.getUnit());
    }
}
