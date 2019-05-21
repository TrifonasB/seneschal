package com.main.seneschal.util;

import java.math.BigDecimal;

public class Quantity {
    private BigDecimal amount;
    private Unit unit;

    public Quantity(BigDecimal amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public Quantity plus(Quantity other) {
        checkForSameUnit(other);
        return new Quantity(amount.add(other.amount), unit);
    }

    public Quantity minus(Quantity other){
        checkForSameUnit(other);
        return new Quantity(amount.subtract(other.amount),unit);
    }

    public void checkForSameUnit(Quantity other){
        if(!(unit.equals(other.unit))){
            throw new IllegalArgumentException("Διαφορετικές μονάδες μέτηρησης");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof Quantity)) {
            return false;
        }

        Quantity theQuantity = (Quantity) other;
        if (unit == null) {
            return theQuantity.unit == null
                    && amount == null && theQuantity.amount == null;
        }

        if (!unit.equals(theQuantity.unit)) {
            return false;
        }

        return amount == null ? theQuantity.amount == null
                : (amount.compareTo(theQuantity.amount) == 0);
    }

    @Override
    public int hashCode() {
        return amount == null ? 0 : amount.hashCode();
    }


}
