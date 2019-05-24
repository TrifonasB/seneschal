package com.main.seneschal.view.ShoppingList;

import com.main.seneschal.dao.Initializer;
import com.main.seneschal.dao.ShoppingListDAO;
import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.util.SimpleCalendar;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AddEditShoppingListPresenterTest {

    private Initializer dataHelper;
    private AddEditShoppingListPresenter presenter;
    private AddEditShoppingListViewStub view;
    private static final int INITIAL_LIST_COUNT = 4;

    @Before
    public void setUp() throws Exception{
        dataHelper = new Initializer();
        dataHelper.prepareData();
        view = new AddEditShoppingListViewStub();
    }

    @Test
    public void testAddNew(){
        presenter = new AddEditShoppingListPresenter(view, new ShoppingListDAO());

        presenter.onSaveShoppingList();
        Assert.assertEquals(view.getErrorTitle(),"Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Εισάγετε το όνομα της λίστας.");

        view.setName("");
        presenter.onSaveShoppingList();
        Assert.assertEquals(view.getErrorTitle(),"Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Εισάγετε το όνομα της λίστας.");

        view.setName("FOOD");
        presenter.onSaveShoppingList();
        Assert.assertEquals(view.getErrorTitle(),"Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(),"Βρέθηκε λίστα με το ίδιο όνομα. Εισάγετε ένα έγκυρο όνομα λίστας.");

        view.setName("NewList");
        view.setCreationDate(new SimpleCalendar(2015, 5, 9));
        view.setSpList(new ArrayList<ListProduct>());
        presenter.onSaveShoppingList();
        Assert.assertEquals(view.getFinishMessage(),"Επιτυχής δημιουργία της λίστας 'NewList'!");
    }

    @Test
    public void testUpdateExisting() {
        view.setAttachedProductName("FOOD");
        presenter = new AddEditShoppingListPresenter(view, dataHelper.getShoppingListDAO());
        presenter.onSaveShoppingList();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής τροποποίηση της λίστας 'FOOD'!");

        int listId = dataHelper.getShoppingListDAO().find("FOOD").getId();
        presenter = new AddEditShoppingListPresenter(view, dataHelper.getShoppingListDAO());
        view.setName("GROCERIES");
        presenter.onSaveShoppingList();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής τροποποίηση της λίστας 'FOOD'!");
        Assert.assertEquals(listId, dataHelper.getShoppingListDAO().find("GROCERIES").getId());
    }

    @Test
    public void testDeletion() {
        view.setAttachedProductName("GROCERIES");
        presenter = new AddEditShoppingListPresenter(view, dataHelper.getShoppingListDAO());
        presenter.onDeleteShoppingList();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής διαγραφή της λίστας 'GROCERIES'!");
        Assert.assertEquals(presenter.getShoppingLists().size(), INITIAL_LIST_COUNT-1);
    }
}
