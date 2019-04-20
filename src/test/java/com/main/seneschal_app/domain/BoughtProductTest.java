package com.main.seneschal_app.domain;

import com.main.seneschal_app.util.Money;
import com.main.seneschal_app.util.Quantity;
import com.main.seneschal_app.util.Unit;

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
        product = new Product("2% FAGE Milk",ProductCategory.DRINK,ProductSubCategory.DAIRY);
        price = Money.euros(new BigDecimal(1.20));
        quantity = new Quantity(new BigDecimal(2), Unit.PIECES);

        boughtProduct.setProduct(product);
        boughtProduct.setPiecePrice(price);
        boughtProduct.setQuantity(quantity);

    }

    @After
    public void tearDown() throws Exception {
        Product.currentProductId=0;
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