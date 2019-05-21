package com.main.seneschal.domain;

import org.junit.*;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setId(1);
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void correctProductSetUp(){
        product.setCategory(ProductCategory.FOOD);
        product.setSubCategory(ProductSubCategory.LUNCH_MEATS);
        product.setName("Sliced Turkey");

        Assert.assertEquals(product.getId(),1);
        Assert.assertEquals(product.getCategory(),ProductCategory.FOOD);
        Assert.assertEquals(product.getSubCategory(),ProductSubCategory.LUNCH_MEATS);
        Assert.assertEquals(product.getName(),"Sliced Turkey");
    }
}