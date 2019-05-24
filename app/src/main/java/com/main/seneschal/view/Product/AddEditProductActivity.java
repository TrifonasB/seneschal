package com.main.seneschal.view.Product;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.main.seneschal.R;
import com.main.seneschal.dao.ProductDAO;
import com.main.seneschal.domain.ProductCategory;
import com.main.seneschal.domain.ProductSubCategory;

public class AddEditProductActivity extends AppCompatActivity implements AddEditProductView {

    TextView txtProductSize;

    @Override
    public String getName() {
        return ((EditText)findViewById(R.id.edtProductName)).getText().toString().trim();
    }

    @Override
    public ProductCategory getCategory() {
        return null;
    }

    @Override
    public ProductSubCategory getSubCategory() {
        return null;
    }

    @Override
    public String getAttachedProductName() {
        return null;
    }

    @Override
    public void setName(String value) {
        ((EditText)findViewById(R.id.edtProductName)).setText(value);
    }

    @Override
    public void setCategory(ProductCategory value) {
        ((EditText)findViewById(R.id.edtProductCat)).setText(value.toString());
    }

    @Override
    public void setSubCategory(ProductSubCategory value) {
        ((EditText)findViewById(R.id.edtProductSubCat)).setText(value.toString());
    }

    @Override
    public void successfullyFinishActivity(String message) {
        Intent retData = new Intent();
        retData.putExtra("message_to_toast", message);
        setResult(RESULT_OK,retData);

    }

    public void showErrorMessage(String title, String message){
        new AlertDialog.Builder(AddEditProductActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null).create().show();
    }

    public void setTxtProductSize(String value){
        txtProductSize.setText(value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);
        txtProductSize = findViewById(R.id.txtProductSize);
        final AddEditProductPresenter presenter = new AddEditProductPresenter(this, new ProductDAO());

        findViewById(R.id.btnNewProduct).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                presenter.onSaveProduct();
            }
        });

    }
}
