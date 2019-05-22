package com.main.seneschal.dao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDAO {

    private PaymentMethodDAO paymentMethodDAO;
    private ProductDAO productDAO;
    private ShoppingListDAO shoppingListDAO;
    private StoreDAO storeDAO;
    private VisitDAO visitDAO;

    private static final int INITIAL_PRODUCT_COUNT = 9;
    private static final int INITIAL_LIST_COUNT = 4;
    private static final int INITIAL_VISIT_COUNT = 1;
    private static final int INITIAL_STORE_COUNT = 2;
    private static final int INITIAL_PAYMENT_METHOD_COUNT = 3;

    @Before
    public void setUp() {
        Initializer dataHelper = new Initializer();
        dataHelper.prepareData();

        paymentMethodDAO = new PaymentMethodDAO();
        productDAO = new ProductDAO();
        shoppingListDAO = new ShoppingListDAO();
        storeDAO = new StoreDAO();
        visitDAO = new VisitDAO();
    }

    @Test
    public void doNothing(){}
}