package com.main.seneschal.dao;

import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.domain.Product;
import com.main.seneschal.domain.ProductCategory;
import com.main.seneschal.domain.ProductSubCategory;
import com.main.seneschal.domain.ShoppingList;
import com.main.seneschal.util.Quantity;
import com.main.seneschal.util.SystemDate;
import com.main.seneschal.util.Unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

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
    public void productsSizeCheck(){
        Assert.assertEquals(INITIAL_PRODUCT_COUNT,productDAO.findAll().size());
    }

    @Test
    public void paymentMethodsSizeCheck(){
        Assert.assertEquals(INITIAL_PAYMENT_METHOD_COUNT,paymentMethodDAO.findAll().size());
    }

    @Test
    public void shoppingListsSizeCheck(){
        Assert.assertEquals(INITIAL_LIST_COUNT,shoppingListDAO.findAll().size());
    }

    @Test
    public void storesSizeCheck(){
        Assert.assertEquals(INITIAL_STORE_COUNT,storeDAO.findAll().size());
    }

    @Test
    public void visitsSizeCheck(){
        Assert.assertEquals(INITIAL_VISIT_COUNT,visitDAO.findAll().size());
    }

    @Test
    public void findNonExistingPayment(){
        Assert.assertNull(paymentMethodDAO.find(3));
    }

    @Test
    public void findNonExistingShoppingList(){
        Assert.assertNull(shoppingListDAO.find("Akyrh lista"));
    }

    @Test
    public void findNonExistingProduct(){
        Assert.assertNull(productDAO.find("Akyro proion"));
    }

    @Test
    public void saveProduct(){
        Product pr = new Product("Dwrean deigma", ProductCategory.FOOD, ProductSubCategory.DAIRY);
        productDAO.save(pr);
        Assert.assertEquals(INITIAL_PRODUCT_COUNT + 1, productDAO.findAll().size());
        Assert.assertEquals(pr, productDAO.find("Dwrean deigma"));
    }

    @Test
    public void saveListWithNewProduct(){
        Product pr = new Product("Dwrean deigma", ProductCategory.FOOD, ProductSubCategory.DAIRY);
        productDAO.save(pr);

        ShoppingList list = new ShoppingList("Nea lista", SystemDate.now(),new ArrayList<ListProduct>());
        list.addProduct(new ListProduct(pr,new Quantity(BigDecimal.ONE, Unit.PIECES)));
        shoppingListDAO.save(list);

        Assert.assertEquals(INITIAL_LIST_COUNT + 1, shoppingListDAO.findAll().size());
        Assert.assertEquals(list,shoppingListDAO.find("Nea lista"));
        Assert.assertEquals(shoppingListDAO.find("Nea lista").getSpList().size(), 1);
        Assert.assertTrue(shoppingListDAO.find("nea lista").indexInList(pr)==0);

    }

    @Test
    public void deleteProductAndDeleteItFromList(){
        Product toBeDeleted = productDAO.find("Melissa no.5");
        productDAO.delete(toBeDeleted);

        ShoppingList list = shoppingListDAO.find("Food");
        list.removeProduct(list.getSpList().get(list.indexInList(toBeDeleted)));

        Assert.assertEquals(INITIAL_PRODUCT_COUNT-1,productDAO.findAll().size());
        Assert.assertNull(productDAO.find("Melissa no.5"));
        Assert.assertEquals(-1, list.indexInList(toBeDeleted));

    }


}