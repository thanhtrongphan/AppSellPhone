package com.example.appsellphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import adapters.MenuAdapter;
import adapters.ProductAdapter;
import function.Database;
import model.Product;


public class ShowProductActivity extends AppCompatActivity {
    RecyclerView menuRecyclerView;
    RecyclerView productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        menuRecyclerView = findViewById(R.id.recycler_menu);
        productRecyclerView = findViewById(R.id.recycler_product);
        // get from database and set to categoriesName
        setMenu();
        setProduct();
    }
    public void setMenu(){
        Database database = new Database(this);
        List<String> categoriesName = database.getCategories();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        menuRecyclerView.setLayoutManager(linearLayoutManager);
        menuRecyclerView.setAdapter(new MenuAdapter(this,categoriesName));
    }
    public void setProduct(){
        Database database = new Database(this);
        List<Product> products = database.getProducts();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(linearLayoutManager);
        productRecyclerView.setAdapter(new ProductAdapter(products,this));
    }

}