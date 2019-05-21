package com.main.seneschal.domain;


public class Product {

    private int id;
    private String name;
    private ProductCategory category;
    private ProductSubCategory subCategory;

    public Product() {
    }

    public Product(int id, String name, ProductCategory category, ProductSubCategory subCategory) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public ProductSubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductSubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
