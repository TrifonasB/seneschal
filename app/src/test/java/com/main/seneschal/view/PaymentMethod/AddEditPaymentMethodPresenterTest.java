package com.main.seneschal.view.PaymentMethod;

import com.main.seneschal.dao.Initializer;
import com.main.seneschal.dao.PaymentMethodDAO;
import com.main.seneschal.domain.Card;
import com.main.seneschal.domain.CardType;
import com.main.seneschal.util.SimpleCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddEditPaymentMethodPresenterTest {

    private Initializer dataHelper;
    private AddEditPaymentMethodPresenter presenter;
    private AddEditPaymentMethodViewStub view;

    private static final int INITIAL_PAYMENT_METHOD_COUNT = 3;

    @Before
    public void setUp() throws Exception {
        dataHelper = new Initializer();
        dataHelper.prepareData();
        view = new AddEditPaymentMethodViewStub();
    }

    @Test
    public void testAddNewCard(){
        presenter = new AddEditPaymentMethodPresenter(view, new PaymentMethodDAO());

        presenter.onSaveCard();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ακριβώς 16 αριθμητικά ψηφία για τον αριθμό της κάρτας.");

        view.setCardNo("2432534632432432432434");
        presenter.onSaveCard();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ακριβώς 16 αριθμητικά ψηφία για τον αριθμό της κάρτας.");

        view.setCardNo("1234567890123456");
        view.setExpirationDate(new SimpleCalendar(2017,1,1));
        presenter.onSaveCard();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε μια μελλοντική ημερομηνία λήξης ώστε η κάρτα να είναι έγκυρη.");

        view.setExpirationDate(new SimpleCalendar(2020,2,1));
        view.setBalance(0);
        presenter.onSaveCard();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ένα θετικό υπόλοιπο ώστε η κάρτα να είναι έγκυρη.");

        view.setBalance(20);
        view.setCardType(CardType.DEBIT);
        presenter.onSaveCard();

        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής προσθήκη της κάρτας με αριθμό 1234567890123456!");
        Assert.assertEquals(presenter.getPaymentMethods().size(),INITIAL_PAYMENT_METHOD_COUNT+1);
    }

    @Test
    public void testUpdateExistingCard(){
        view.setAttachedPaymentMethodID(1);
        presenter = new AddEditPaymentMethodPresenter(view,dataHelper.getPaymentMethodDAO());

        presenter.onSaveCard();
        Assert.assertEquals(view.getFinishMessage(),"Επιτυχής τροποποίηση της κάρτας με αριθμό 3250992199340248!");
    }

    @Test
    public void testDeleteCard(){
        view.setAttachedPaymentMethodID(2);
        presenter = new AddEditPaymentMethodPresenter(view,dataHelper.getPaymentMethodDAO());

        presenter.onDeleteCard();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής διαγραφή της κάρτας με αριθμό 4756332100095460!");
        Assert.assertEquals(presenter.getPaymentMethods().size(), INITIAL_PAYMENT_METHOD_COUNT-1);
    }

    @Test
    public void testUpdateWallet(){
        view.setAttachedPaymentMethodID(0);
        presenter = new AddEditPaymentMethodPresenter(view, dataHelper.getPaymentMethodDAO());

        view.setBalance(-2);
        presenter.onUpdateWalletBalance();
        Assert.assertEquals(view.getErrorTitle(), "Σφάλμα!");
        Assert.assertEquals(view.getErrorMessage(), "Συμπληρώστε ένα θετικό υπόλοιπο για το πορτοφόλι.");

        view.setBalance(50);
        presenter.onUpdateWalletBalance();
        Assert.assertEquals(view.getFinishMessage(), "Επιτυχής ενημέρωση του πορτοφολιού.");

    }
}