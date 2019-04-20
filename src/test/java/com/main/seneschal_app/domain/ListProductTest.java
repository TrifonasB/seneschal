package com.main.seneschal_app.domain;

import com.main.seneschal_app.util.Quantity;
import com.main.seneschal_app.util.Unit;

import org.junit.*;

import java.math.BigDecimal;

public class ListProductTest {

    private Product product;
    private Quantity quantity;
    private ListProduct lp;

    @Before
    public void setUp (){
        product = new Product("kaseri bouras", ProductCategory.FOOD, ProductSubCategory.DAIRY);
        quantity = new Quantity(BigDecimal.ONE, Unit.Kg);
        lp = new ListProduct(product, quantity);

        product = new Product("Kaseri Bouras", ProductCategory.FOOD, ProductSubCategory.DAIRY);
        lp.setProduct(product);
        quantity = new Quantity(BigDecimal.TEN, Unit.PIECES);
        lp.setQuantity(quantity);
    }

    @After
    public void tearDown() {
        product.currentProductId=0;
    }

    @Test
    public void getTest(){
        Assert.assertEquals(lp.getProduct(), product);
        Assert.assertEquals(lp.getQuantity(), quantity);
    }

    @Test
    public void finalTest() {

        Quantity quantity1 = new Quantity(new BigDecimal(6), Unit.PIECES);
        lp.removeQuantity(quantity1);
        Assert.assertFalse(lp.isBought());
        Assert.assertEquals(lp.getQuantity().getAmount(), new BigDecimal(4));

        lp.removeQuantity(quantity1);
        Assert.assertTrue(lp.isBought());
        Assert.assertEquals(lp.getQuantity().getAmount(), BigDecimal.ZERO);

    }
}
