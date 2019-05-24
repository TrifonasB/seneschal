package com.main.seneschal.view.Product.AddEditProduct;

import com.main.seneschal.dao.Initializer;
import com.main.seneschal.dao.ProductDAO;
import com.main.seneschal.domain.ProductCategory;
import com.main.seneschal.domain.ProductSubCategory;
import com.main.seneschal.view.Product.AddEditProductViewStub;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddEditProductPresenterTest {

    private Initializer dataHelper;
    private AddEditProductPresenter presenter;
    private AddEditProductViewStub view;

    @Before
    public void setUp() throws Exception {
        dataHelper = new Initializer();
        dataHelper.prepareData();
        view = new AddEditProductViewStub();
    }

    @Test
    public void testAddNew(){
        presenter = new AddEditProductPresenter(view,new ProductDAO());

        presenter.onSaveProduct();
        Assert.assertEquals(view.getErrorTitle(),"Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Εισάγετε το όνομα του προϊόντος.");

        view.setName("");
        presenter.onSaveProduct();
        Assert.assertEquals(view.getErrorTitle(),"Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Εισάγετε το όνομα του προϊόντος.");


        view.setName("Neo proion");
        view.setCategory(ProductCategory.FOOD);
        view.setSubCategory(ProductSubCategory.DAIRY);
        presenter.onSaveProduct();

        Assert.assertEquals(view.getFinishMessage(),"Επιτυχής προσθήκη του προϊόντος 'Neo proion'!");

    }

    @Test
    public void testUpdateExisting(){
        view.setAttachedProductName("Melissa no.5");
        presenter = new AddEditProductPresenter(view, dataHelper.getProductDAO());

        presenter.onSaveProduct();
        Assert.assertEquals(view.getFinishMessage(),"Επιτυχής τροποποίηση του προϊόντος 'Melissa no.5'!");

        int prodID = dataHelper.getProductDAO().find("Melissa no.5").getId();
        presenter = new AddEditProductPresenter(view, dataHelper.getProductDAO());
        view.setName("Melissa no.6");

        presenter.onSaveProduct();
        Assert.assertEquals(view.getFinishMessage(),"Επιτυχής τροποποίηση του προϊόντος 'Melissa no.5'!");
        Assert.assertEquals(prodID, dataHelper.getProductDAO().find("Melissa no.6").getId());
    }


}