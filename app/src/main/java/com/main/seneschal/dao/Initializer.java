package com.main.seneschal.dao;

import com.main.seneschal.domain.Address;
import com.main.seneschal.domain.BoughtProduct;
import com.main.seneschal.domain.Card;
import com.main.seneschal.domain.CardType;
import com.main.seneschal.domain.DaySchedule;
import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.domain.PaymentMethod;
import com.main.seneschal.domain.Product;
import com.main.seneschal.domain.ProductCategory;
import com.main.seneschal.domain.ProductSubCategory;
import com.main.seneschal.domain.ShoppingList;
import com.main.seneschal.domain.Store;
import com.main.seneschal.domain.Visit;
import com.main.seneschal.domain.Wallet;
import com.main.seneschal.util.Money;
import com.main.seneschal.util.Quantity;
import com.main.seneschal.util.SimpleCalendar;
import com.main.seneschal.util.SystemDate;
import com.main.seneschal.util.Unit;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Initializer {

    protected void eraseData(){

        for(PaymentMethod paymentMethod : getPaymentMethodDAO().findAll()){
            getPaymentMethodDAO().delete(paymentMethod);
        }

        for(Product product : getProductDAO().findAll()){
            getProductDAO().delete(product);
        }

        for(ShoppingList shoppingList : getShoppingListDAO().findAll()){
            getShoppingListDAO().delete(shoppingList);
        }

        for(Store store : getStoreDAO().findAll()){
            getStoreDAO().delete(store);
        }

        for(Visit visit : getVisitDAO().findAll()){
            getVisitDAO().delete(visit);
        }

        getProductDAO().reset();
        getPaymentMethodDAO().reset();
        getVisitDAO().reset();
        getStoreDAO().reset();
        getShoppingListDAO().reset();
    }

    public void prepareData(){
        eraseData();

        PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
        paymentMethodDAO.save(new Wallet(Money.euros(100)));
        paymentMethodDAO.save(new Card("3250992199340248",new SimpleCalendar(2020,2,1), CardType.CREDIT, Money.euros(100)));
        paymentMethodDAO.save(new Card("4756332100095460",new SimpleCalendar(2016, 10,1),CardType.DEBIT,Money.euros(50)));


        ProductDAO productDAO = new ProductDAO();
        productDAO.save(new Product("Kaseri Bouras", ProductCategory.FOOD, ProductSubCategory.DAIRY));
        productDAO.save(new Product("2% FAGE Milk", ProductCategory.DRINK, ProductSubCategory.DAIRY));
        productDAO.save(new Product("Minerva Elaiolado", ProductCategory.FOOD, ProductSubCategory.OTHER));
        productDAO.save(new Product("Colgate White", ProductCategory.HYGIENE, ProductSubCategory.BATHROOM_PRODUCTS));
        productDAO.save(new Product("Head & Shoulders Shampoo", ProductCategory.HYGIENE, ProductSubCategory.BATHROOM_PRODUCTS));
        productDAO.save(new Product("Mythos 330ml", ProductCategory.DRINK, ProductSubCategory.ALCOHOLIC_DRINKS));
        productDAO.save(new Product("Melissa no.5", ProductCategory.FOOD, ProductSubCategory.PASTA));
        productDAO.save(new Product("Sprite 500ml", ProductCategory.DRINK, ProductSubCategory.SODAS));
        productDAO.save(new Product("STR8 Aftershave",ProductCategory.HYGIENE,ProductSubCategory.BATHROOM_PRODUCTS));


        ShoppingListDAO shoppingListDAO = new ShoppingListDAO();

        ShoppingList foodList = new ShoppingList();
        foodList.setName("Food");
        foodList.setCreationDate(SystemDate.now());
        foodList.addProduct(new ListProduct(productDAO.find("Kaseri Bouras"),new Quantity(BigDecimal.valueOf(5), Unit.Kg)));
        foodList.addProduct(new ListProduct(productDAO.find("2% FAGE Milk"),new Quantity(BigDecimal.valueOf(2),Unit.PIECES)));
        foodList.addProduct(new ListProduct(productDAO.find("Minerva Elaiolado"), new Quantity(BigDecimal.TEN,Unit.PIECES)));
        foodList.addProduct(new ListProduct(productDAO.find("Melissa no.5"),new Quantity (BigDecimal.valueOf(3),Unit.PIECES)));
        shoppingListDAO.save(foodList);

        ShoppingList sodaList = new ShoppingList();
        sodaList.setName("Sodas");
        sodaList.setCreationDate(new SimpleCalendar(2019,5,2));
        sodaList.addProduct(new ListProduct(productDAO.find("Mythos 330ml"), new Quantity (BigDecimal.valueOf(12),Unit.PIECES)));
        sodaList.addProduct(new ListProduct(productDAO.find("Sprite 500ml"), new Quantity (BigDecimal.valueOf(20),Unit.PIECES)));
        shoppingListDAO.save(sodaList);

        ShoppingList bathList = new ShoppingList();
        bathList.setName("Gia to mpanio");
        bathList.setCreationDate(new SimpleCalendar(2019, 5, 12));
        bathList.addProduct(new ListProduct(productDAO.find("Colgate White"),new Quantity (BigDecimal.ONE,Unit.PIECES)));
        bathList.addProduct(new ListProduct(productDAO.find("Head & Shoulders Shampoo"),new Quantity (BigDecimal.valueOf(2),Unit.PIECES)));
        bathList.addProduct(new ListProduct(productDAO.find("STR8 Aftershave"),new Quantity (BigDecimal.valueOf(15),Unit.PIECES)));
        shoppingListDAO.save(bathList);

        ShoppingList randomBoughtList = new ShoppingList();
        randomBoughtList.setName("Agorasmena proionta");
        randomBoughtList.setCreationDate(new SimpleCalendar(2018,2,28));
        randomBoughtList.addProduct(new ListProduct(productDAO.find("Colgate White"), new Quantity(BigDecimal.ZERO,Unit.PIECES)));
        randomBoughtList.addProduct(new ListProduct(productDAO.find("Mythos 330ml"), new Quantity(BigDecimal.ZERO,Unit.PIECES)));
        randomBoughtList.addProduct(new ListProduct(productDAO.find("2% FAGE Milk"), new Quantity(BigDecimal.ZERO,Unit.PIECES)));
        shoppingListDAO.save(randomBoughtList);


        StoreDAO storeDAO = new StoreDAO();
        storeDAO.save(new Store("Kivotos", new DaySchedule[7],new Address("Papaioannou", "23", "karnavalia")));
        storeDAO.save(new Store("AB Vasilopoulos", new DaySchedule[7], new Address ("Perikleous", "10", "Peristeri")));


        VisitDAO visitDAO = new VisitDAO();
        ArrayList<ShoppingList> visitLists = new ArrayList<>();
        visitLists.add(shoppingListDAO.find("Agorasmena proionta"));
        Visit v1 = new Visit(new SimpleCalendar(2018,3,1),storeDAO.find(1),paymentMethodDAO.find(0),visitLists);
        v1.addProduct(new BoughtProduct(productDAO.find("Colgate White"),new Quantity(BigDecimal.ONE,Unit.PIECES), Money.euros(2)));
        v1.addProduct(new BoughtProduct(productDAO.find("Mythos 330ml"),new Quantity(BigDecimal.valueOf(6),Unit.PIECES),Money.euros(3)));
        v1.addProduct(new BoughtProduct(productDAO.find("2% FAGE Milk"),new Quantity(BigDecimal.TEN,Unit.PIECES),Money.euros(12)));
        visitDAO.save(v1);


    }



    public PaymentMethodDAO getPaymentMethodDAO(){
        return new PaymentMethodDAO();
    }

    public ProductDAO getProductDAO(){
        return new ProductDAO();
    }

    public ShoppingListDAO getShoppingListDAO(){
        return new ShoppingListDAO();
    }

    public StoreDAO getStoreDAO(){
        return new StoreDAO();
    }

    public VisitDAO getVisitDAO(){
        return new VisitDAO();
    }

}
