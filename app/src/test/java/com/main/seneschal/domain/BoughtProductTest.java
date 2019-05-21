package com.main.seneschal.domain;

import com.main.seneschal.util.Money;
import com.main.seneschal.util.Quantity;
import com.main.seneschal.util.Unit;

import org.junit.*;

import java.math.BigDecimal;




public class BoughtProductTest {

    private BoughtProduct boughtProduct;
    private Product product;
    private Money price;
    private Quantity quantity;

    @Before
    public void setUp() throws Exception {
        boughtProduct = new BoughtProduct();
        product = new Product(1,"2% FAGE Milk",ProductCategory.DRINK,ProductSubCategory.DAIRY);
        price = Money.euros(new BigDecimal(1.20));
        quantity = new Quantity(new BigDecimal(2), Unit.PIECES);

        boughtProduct.setProduct(product);
        boughtProduct.setPiecePrice(price);
        boughtProduct.setQuantity(quantity);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void correctBoughtProductSetup() {
        Assert.assertEquals(boughtProduct.getProduct(),product);
        Assert.assertEquals(boughtProduct.getPiecePrice(),price);
        Assert.assertEquals(boughtProduct.getQuantity(),quantity);
    }

    @Test
    public void totalPriceCalculation(){
        Assert.assertEquals(boughtProduct.getTotalPrice(),Money.euros(new BigDecimal(2.40)));
    }
}