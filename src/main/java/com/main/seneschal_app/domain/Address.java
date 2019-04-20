package com.main.seneschal_app.domain;

public class Address {
    private String street;
    private String number;
    private String area;

    public Address() {
    }

    public Address(String street, String number, String area) {
        this.street = street;
        this.number = number;
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
