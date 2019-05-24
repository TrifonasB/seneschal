package com.main.seneschal.view.ShoppingList;

import com.main.seneschal.dao.ShoppingListDAO;
import com.main.seneschal.domain.ListProduct;
import com.main.seneschal.domain.ShoppingList;
import com.main.seneschal.util.SimpleCalendar;

import java.util.ArrayList;
import java.util.List;

public class AddEditShoppingListPresenter {

    private AddEditShoppingListView view;
    private ShoppingListDAO lists;
    ShoppingList attachedSpList;

    public AddEditShoppingListPresenter (AddEditShoppingListView view, ShoppingListDAO lists){
        this.view = view;
        this.lists = lists;

        String attachedListName = view.getAttachedListName();
        attachedSpList = attachedListName == null ? null : lists.find(attachedListName);

        if (attachedListName != null){
            view.setName(attachedSpList.getName());
            view.setCreationDate(attachedSpList.getCreationDate());
            view.setSpList(attachedSpList.getSpList());
        }
    }

    public void onSaveShoppingList() {
        String name = view.getName();
        SimpleCalendar creationDate = view.getCreationDate();
        ArrayList<ListProduct> spList = view.getSpList();

        if (name.length() == 0){
            view.showErrorMessage("Σφάλμα!", "Εισάγετε το όνομα της λίστας.");
        }else{
            if (attachedSpList == null){
                if (lists.find(name) != null){
                    view.showErrorMessage("Σφάλμα!", "Βρέθηκε λίστα με το ίδιο όνομα. Εισάγετε ένα έγκυρο όνομα λίστας.");
                }else {
                    ShoppingList spListTemp = new ShoppingList(name, creationDate, spList);
                    lists.save(spListTemp);
                    view.successfullyFinishActivity("Επιτυχής δημιουργία της λίστας '" + name + "'!");
                }
            }else{
                String oldName = attachedSpList.getName();
                attachedSpList.setName(name);
                attachedSpList.setCreationDate(creationDate);
                attachedSpList.setSpList(spList);

                view.successfullyFinishActivity("Επιτυχής τροποποίηση της λίστας '"+ oldName +"'!");
            }
        }
    }

    public void onDeleteShoppingList(){
        String name = view.getName();
        lists.delete(attachedSpList);
        view.successfullyFinishActivity("Επιτυχής διαγραφή της λίστας '" +name+ "'!");
    }

    public List<ShoppingList> getShoppingLists() {return lists.findAll();}

}
