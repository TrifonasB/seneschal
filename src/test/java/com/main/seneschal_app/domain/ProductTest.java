package com.main.seneschal_app.domain;

import org.junit.*;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        product.setId();
    }

    @After
    public void tearDown() throws Exception {
        Product.currentProductId=0;
    }

    @Test
    public void productIDsAfterOneProduct() {
        Assert.assertEquals(Product.currentProductId,1);
    }

    @Test
    public void productIDsAfterTwoProducts(){
        Product product2 = new Product();
        product2.setId();
        Assert.assertEquals(Product.currentProductId, 2);
    }

    @Test
    public void correctProductSetUp(){
        product.setId();
        product.setCategory(ProductCategory.FOOD);
        product.setSubCategory(ProductSubCategory.LUNCH_MEATS);
        product.setName("Sliced Turkey");

        Assert.assertEquals(product.getId(),Product.currentProductId-1);
        Assert.assertEquals(product.getCategory(),ProductCategory.FOOD);
        Assert.assertEquals(product.getSubCategory(),ProductSubCategory.LUNCH_MEATS);
        Assert.assertEquals(product.getName(),"Sliced Turkey");
    }
}