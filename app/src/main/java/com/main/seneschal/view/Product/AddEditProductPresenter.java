package com.main.seneschal.view.Product;

import com.main.seneschal.dao.ProductDAO;
import com.main.seneschal.domain.Product;
import com.main.seneschal.domain.ProductCategory;
import com.main.seneschal.domain.ProductSubCategory;

import java.util.List;

public class AddEditProductPresenter {

    private AddEditProductView view;
    private ProductDAO products;
    Product attachedProduct;

    public AddEditProductPresenter(AddEditProductView view, ProductDAO products){
        this.view = view;
        this.products = products;

        String attachedProductName = view.getAttachedProductName();
        attachedProduct = attachedProductName == null ? null : products.find(attachedProductName);

        if(attachedProductName !=null){
            view.setName(attachedProduct.getName());
            view.setCategory(attachedProduct.getCategory());
            view.setSubCategory(attachedProduct.getSubCategory());
        }
    }

    public void onSaveProduct(){
        String name = view.getName();
        ProductCategory category = view.getCategory();
        ProductSubCategory subCategory = view.getSubCategory();

        if(name.length()==0)
            view.showErrorMessage("Σφάλμα!", "Εισάγετε το όνομα του προϊόντος.");
        else{
            if(attachedProduct == null){
                if(products.find(name)!= null){
                    view.showErrorMessage("Σφάλμα!", "Βρέθηκε προϊόν με το ίδιο όνομα. Εισάγετε ένα έγκυρο όνομα προϊόντος.");
                }
                else {
                    Product productTmp = new Product(name, category, subCategory);

                    products.save(productTmp);
                    view.successfullyFinishActivity("Επιτυχής προσθήκη του προϊόντος '" + name + "'!");
                }
            }else{
                String oldName = attachedProduct.getName();

                attachedProduct.setName(name);
                attachedProduct.setCategory(category);
                attachedProduct.setSubCategory(subCategory);


                view.successfullyFinishActivity("Επιτυχής τροποποίηση του προϊόντος '" +oldName+ "'!");
            }
        }
    }

    public void onDeleteProduct(){
        String name = view.getName();

        products.delete(attachedProduct);

        view.successfullyFinishActivity("Επιτυχής διαγραφή του προϊόντος '" +name+ "'!");
    }

    public List<Product> getProducts(){
        return products.findAll();
    }

}
