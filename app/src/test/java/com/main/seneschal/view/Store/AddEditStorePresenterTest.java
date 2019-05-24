package com.main.seneschal.view.Store;

import com.main.seneschal.dao.Initializer;
import com.main.seneschal.dao.StoreDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AddEditStorePresenterTest {
    private Initializer dataHelper;
    private AddEditStorePresenter presenter;
    private AddEditStoreViewStub view;

    private static final int INITIAL_STORE_COUNT = 2;

    @Before
    public void setUp() throws Exception {
        dataHelper = new Initializer();
        dataHelper.prepareData();
        view = new AddEditStoreViewStub();
    }

    @Test
    public void testAddNew(){
        presenter = new AddEditStorePresenter(view, new StoreDAO());

        presenter.onSaveStore();
        Assert.assertEquals(view.getErrorTitle(),"Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Εισάγετε το όνομα του καταστήματος.");

        view.setName("");
        presenter.onSaveStore();
        Assert.assertEquals(view.getErrorTitle(),"Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Εισάγετε το όνομα του καταστήματος.");

        view.setName("New store");
        presenter.onSaveStore();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής προσθήκη του καταστήματος 'New store'!");
        Assert.assertEquals(presenter.getStores().size(), INITIAL_STORE_COUNT+1);
    }

    @Test
    public void testUpdateExisting(){
        view.setAttachedStoreID(0);
        presenter = new AddEditStorePresenter(view, dataHelper.getStoreDAO());

        presenter.onSaveStore();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής τροποποίηση του καταστήματος 'Kivotos'!");
    }

    @Test
    public void testStoreDeletion(){
        view.setAttachedStoreID(1);
        presenter = new AddEditStorePresenter(view, dataHelper.getStoreDAO());

        presenter.onDeleteStore();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής διαγραφή του καταστήματος 'AB Vasilopoulos'!");
        Assert.assertEquals(presenter.getStores().size(),INITIAL_STORE_COUNT-1);
    }
}