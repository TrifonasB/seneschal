package com.main.seneschal_app.domain;


import com.main.seneschal_app.util.Money;
import com.main.seneschal_app.util.Quantity;

public class BoughtProduct {
    private Product product;
    private Quantity quantity;
    private Money piecePrice;

    public BoughtProduct() {}

    public BoughtProduct(Product product, Quantity quantity, Money piecePrice) {
        this.product = product;
        this.quantity = quantity;
        this.piecePrice = piecePrice;
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

    public Money getPiecePrice() {
        return piecePrice;
    }

    public void setPiecePrice(Money piecePrice) {
        this.piecePrice = piecePrice;
    }

    public Money getTotalPrice(){
        return Money.euros(piecePrice.getAmount().multiply(quantity.getAmount()));
    }


}
