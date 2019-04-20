package com.main.seneschal_app.domain;

import com.main.seneschal_app.util.Money;
import com.main.seneschal_app.util.Quantity;
import com.main.seneschal_app.util.SystemDate;
import com.main.seneschal_app.util.Unit;

import org.junit.*;

import java.math.BigDecimal;
import java.util.ArrayList;


public class ShoppingListTest {

    private ArrayList<ListProduct> spList;
    private ShoppingList shoppingList;
    private Product pr1, pr2;

    @Before
    public void setUp() throws Exception {
        shoppingList = new ShoppingList("lista", SystemDate.now());
        pr1 = new Product("Kaseri Bouras", ProductCategory.FOOD, ProductSubCategory.DAIRY);
        pr2 = new Product("2% FAGE Milk", ProductCategory.DRINK, ProductSubCategory.DAIRY);
    }

    @After
    public void tearDown() throws Exception {
        shoppingList.currentListId=0;
        pr1.currentProductId=0;
    }

    @Test
    public void activity () {
        ListProduct lp1 = new ListProduct(pr1, new Quantity(new BigDecimal(3), Unit.Kg));
        shoppingList.addProduct(lp1);
        Assert.assertTrue(shoppingList.isActive());
        Assert.assertEquals(shoppingList.getSpList().size(), 1);
    }

    @Test
    public void removal () {
        ListProduct lp1 = new ListProduct(pr1, new Quantity(new BigDecimal(3), Unit.Kg));
        ListProduct lp2 = new ListProduct(pr2, new Quantity(new BigDecimal(1), Unit.L));
        shoppingList.addProduct(lp1);
        shoppingList.addProduct(lp2);
        Assert.assertEquals(shoppingList.getSpList().size(), 2);
        Assert.assertTrue(shoppingList.contains(pr1));
        shoppingList.removeProduct(lp1);
        Assert.assertEquals(shoppingList.getSpList().size(), 1);

    }

    @Test
    public void quantityRemoval () {
        ListProduct lp1 = new ListProduct(pr1, new Quantity(new BigDecimal(3), Unit.Kg));
        ListProduct lp2 = new ListProduct(pr2, new Quantity(new BigDecimal(1), Unit.L));
        shoppingList.addProduct(lp1);
        shoppingList.addProduct(lp2);
        BoughtProduct bp = new BoughtProduct(pr1, new Quantity(BigDecimal.ONE, Unit.Kg),Money.euros(4));
        shoppingList.removeBoughtQuantity(bp);
        Assert.assertEquals(shoppingList.getSpList().get(0).getQuantity().getAmount(), new BigDecimal(2));
    }

    @Test
    public void quantityRemovalFullExtra () {
        ListProduct lp1 = new ListProduct(pr1, new Quantity(new BigDecimal(3), Unit.Kg));
        ListProduct lp2 = new ListProduct(pr2, new Quantity(new BigDecimal(1), Unit.L));
        shoppingList.addProduct(lp1);
        shoppingList.addProduct(lp2);
        BoughtProduct bp = new BoughtProduct(pr1, new Quantity(new BigDecimal(4), Unit.Kg),Money.euros(4));
        shoppingList.removeBoughtQuantity(bp);
        Assert.assertEquals(shoppingList.getSpList().get(0).getQuantity().getAmount(), new BigDecimal(0));
    }

    @Test
    public void quantityRemovalFullExtraSuper () {
        ListProduct lp1 = new ListProduct(pr1, new Quantity(new BigDecimal(3), Unit.Kg));
        ListProduct lp2 = new ListProduct(pr2, new Quantity(new BigDecimal(1), Unit.L));
        shoppingList.addProduct(lp1);
        shoppingList.addProduct(lp2);
        BoughtProduct bp1 = new BoughtProduct(pr1, new Quantity(new BigDecimal(4), Unit.Kg),Money.euros(4));
        BoughtProduct bp2 = new BoughtProduct(pr2, new Quantity(new BigDecimal(1), Unit.L),Money.euros(2));
        shoppingList.removeBoughtQuantity(bp1);
        shoppingList.removeBoughtQuantity(bp2);
        Assert.assertFalse(shoppingList.isActive());

    }

}















