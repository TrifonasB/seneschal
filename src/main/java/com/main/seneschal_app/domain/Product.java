package com.main.seneschal_app.domain;


public class Product {
    protected static int currentProductId=0;

    private int id;
    private String name;
    private ProductCategory category;
    private ProductSubCategory subCategory;

    public Product() {
    }

    public Product(String name, ProductCategory category, ProductSubCategory subCategory) {
        id = currentProductId;
        currentProductId++;

        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = currentProductId;
        currentProductId++;
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
