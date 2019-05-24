package com.main.seneschal.domain;

import com.main.seneschal.util.Money;
import com.main.seneschal.util.Quantity;
import com.main.seneschal.util.SimpleCalendar;
import com.main.seneschal.util.SystemDate;
import com.main.seneschal.util.Unit;

import org.junit.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VisitTest {

    private Visit visit;
    private Product product;
    private ShoppingList sLists;
    private SimpleCalendar visitDate;
    private ArrayList<BoughtProduct> bpList;
    private Store visitedStore;
    private Money total;
    private PaymentMethod paymentMethod;


    @Before
    public void setUp() {
        visit = new Visit(new SimpleCalendar(2019, 3, 23), new Store(), paymentMethod, new ArrayList<ShoppingList>());
        product = new Product("Kaseri Bouras", ProductCategory.FOOD, ProductSubCategory.DAIRY);
        sLists = new ShoppingList();
        ListProduct lp = new ListProduct(product, new Quantity(new BigDecimal(3), Unit.L));
        sLists.addProduct(lp);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test1 () {
        bpList = new ArrayList<>();
        visit.setBpList(bpList);

        paymentMethod = new Wallet();
        visit.setPaymentMethod(paymentMethod);

        total = Money.euros(2);
        visit.setTotal(total);

        visitDate = SystemDate.now();
        visit.setVisitDate(visitDate);

        visitedStore = new Store();
        visit.setVisitedStore(visitedStore);

        Assert.assertEquals(visit.getBpList(), bpList);
        Assert.assertEquals(visit.getId(), 0);
        Assert.assertEquals(visit.getPaymentMethod(), paymentMethod);
        Assert.assertEquals(visit.getTotal(), Money.euros(2));
        Assert.assertEquals(visit.getVisitDate(), SystemDate.now());
        Assert.assertEquals(visit.getVisitedStore(), visitedStore);
    }

    @Test
    public void listAdditionTest () {
        visit.addList(sLists);
        Assert.assertEquals(visit.getVisitLists().get(0), sLists);
        Assert.assertEquals(visit.getVisitLists().size(), 1);
    }

    @Test
    public void listNullAdditionTest() {
        visit.addList(null);
        Assert.assertEquals(visit.getVisitLists().size(), 1);
        Assert.assertNotNull(visit.getVisitLists().get(0));
    }

    @Test
    public void listRemovalTest() {
        visit.addList(sLists);
        visit.removeList(sLists);
        Assert.assertEquals(visit.getVisitLists().size(), 0);
    }

    @Test
    public void afterAddingTwoProducts() {
        BoughtProduct bp1 = new BoughtProduct(product, new Quantity(BigDecimal.TEN, Unit.PIECES), Money.euros(90));
        visit.addProduct(bp1);
        Assert.assertEquals(visit.getBpList().size(), 1);
        Assert.assertEquals(visit.getTotal(), Money.euros(900));

        product = new Product("2% FAGE Milk", ProductCategory.DRINK, ProductSubCategory.DAIRY);
        BoughtProduct bp2 = new BoughtProduct(product, new Quantity(BigDecimal.ONE, Unit.PIECES), Money.euros(5));
        visit.addProduct(bp2);
        Assert.assertEquals(visit.getTotal(), Money.euros(905));
        Assert.assertEquals(visit.getBpList().size(), 2);
    }

    @Test
    public void productRemoval() {
        BoughtProduct bp = new BoughtProduct(product, new Quantity(BigDecimal.TEN, Unit.PIECES), Money.euros(75));
        visit.addProduct(bp);
        visit.removeProduct(bp);
        Assert.assertEquals(visit.getBpList().size(), 0);
        Assert.assertEquals(visit.getTotal(), Money.euros(0));
    }

    @Test
    public void removeWithContain () {
        BoughtProduct bp = new BoughtProduct(product, new Quantity(BigDecimal.TEN, Unit.PIECES), Money.euros(75));
        visit.addProduct(bp);
        Assert.assertTrue(visit.contains(product));
        visit.removeProduct(bp);
        Assert.assertFalse(visit.contains(product));
    }

    @Test
    public void adjustments () {
        BoughtProduct bp = new BoughtProduct(product, new Quantity(BigDecimal.TEN, Unit.PIECES), Money.euros(75));
        Quantity quantity = new Quantity(new BigDecimal(4), Unit.PIECES);
        visit.addProduct(bp);
        Assert.assertEquals(visit.getTotal(), Money.euros(750));
        visit.adjustQuantity(bp, quantity);
        Assert.assertEquals(visit.getTotal(), Money.euros(300));
    }

    @Test
    public void theBudget () {
        paymentMethod = new Wallet(Money.euros(1000));
        visit.setPaymentMethod(paymentMethod);
        BoughtProduct bp = new BoughtProduct(product, new Quantity(BigDecimal.TEN, Unit.PIECES), Money.euros(75));
        visit.addProduct(bp);
        Assert.assertTrue(visit.closeToBudget(Money.euros(300)));
        Assert.assertFalse(visit.closeToBudget(Money.euros(200)));
    }
}


















