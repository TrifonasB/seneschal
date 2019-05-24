package com.main.seneschal.view.Product.AddEditProduct;

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
                Product productTmp = new Product(name,category,subCategory);
                products.save(productTmp);

                view.successfullyFinishActivity("Επιτυχής προσθήκη του προϊόντος '" +name+ "'!");

            }else{
                String oldName = attachedProduct.getName();

                attachedProduct.setName(name);
                attachedProduct.setCategory(category);
                attachedProduct.setSubCategory(subCategory);


                view.successfullyFinishActivity("Επιτυχής τροποποίηση του προϊόντος '" +oldName+ "'!");
            }
        }
    }

    public List<Product> getProducts(){
        return products.findAll();
    }

}
