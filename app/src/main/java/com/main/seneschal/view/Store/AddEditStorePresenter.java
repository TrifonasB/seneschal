package com.main.seneschal.view.Store;

import com.main.seneschal.dao.StoreDAO;
import com.main.seneschal.domain.Address;
import com.main.seneschal.domain.DaySchedule;
import com.main.seneschal.domain.Store;

import java.util.List;

public class AddEditStorePresenter {

    AddEditStoreView view;
    StoreDAO stores;
    Store attachedStore;

    public AddEditStorePresenter(AddEditStoreView view, StoreDAO stores) {
        this.view = view;
        this.stores = stores;

        Integer attachedStoreID = view.getAttachedStoreID();
        attachedStore = attachedStoreID == null ? null : stores.find(attachedStoreID);

        if(attachedStore!=null){
            view.setName(attachedStore.getName());
            view.setSchedule(attachedStore.getSchedule());
            view.setAddress(attachedStore.getAddress());
        }

    }

    public void onSaveStore(){
        String name = view.getName();
        DaySchedule[] schedule = view.getSchedule();
        Address address = view.getAddress();

        if(name.length()==0)
            view.showErrorMessage("Σφάλμα!", "Εισάγετε το όνομα του καταστήματος.");
        else{
            if(attachedStore == null){
                Store storeTmp = new Store(name,schedule,address);
                stores.save(storeTmp);

                view.successfullyFinishActivity("Επιτυχής προσθήκη του καταστήματος '" + name + "'!");
            }else{
                String oldName = attachedStore.getName();

                attachedStore.setName(name);
                attachedStore.setSchedule(schedule);
                attachedStore.setAddress(address);

                view.successfullyFinishActivity("Επιτυχής τροποποίηση του καταστήματος '" + oldName + "'!");
            }
        }
    }

    public void onDeleteStore(){
        String name = view.getName();

        stores.delete(attachedStore);

        view.successfullyFinishActivity("Επιτυχής διαγραφή του καταστήματος '"+name+"'!");
    }

    public List<Store> getStores(){return stores.findAll();}


}
