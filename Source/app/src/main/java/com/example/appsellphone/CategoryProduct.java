package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapters.ProductAdapter;
import function.Database;
import model.Product;

public class CategoryProduct extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnHighToLow, btnLowToHigh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        int idCategory = getIntent().getIntExtra("idCategory", 1);
        setInit();
        setProductByIdCategory(idCategory);
        setButtonSort(idCategory);
    }

    private void setButtonSort(int idCategory) {
        Database database = new Database(this);
        List<Product> products;
        if (idCategory == 0){
            products = database.getProducts();
        }
        else {
            products = database.getProductbyIdCategory(idCategory);
        }
        btnHighToLow.setOnClickListener(v -> {
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return Double.compare(Double.valueOf(p2.getPrice()), Double.valueOf(p1.getPrice()));
                }
            });
            int numberColumn = 2;
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,  numberColumn);
            recyclerView.setLayoutManager(layoutManager);
            ProductAdapter productAdapter = new ProductAdapter(products,this );
            recyclerView.setAdapter(productAdapter);
        });

        btnLowToHigh.setOnClickListener(v -> {
            Collections.sort(products, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return Double.compare(Double.valueOf(p1.getPrice()), Double.valueOf(p2.getPrice()));
                }
            });
            int numberColumn = 2;
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,  numberColumn);
            recyclerView.setLayoutManager(layoutManager);
            ProductAdapter productAdapter = new ProductAdapter(products,this );
            recyclerView.setAdapter(productAdapter);
        });
    }

    private void setProductByIdCategory(int idCategory) {
        if (idCategory == 0) {
            Database database = new Database(this);
            List<Product> products = database.getProducts();
            int numberColumn = 2;
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,  numberColumn);
            recyclerView.setLayoutManager(layoutManager);
            ProductAdapter productAdapter = new ProductAdapter(products,this );
        recyclerView.setAdapter(productAdapter);
        } else {
            Database database = new Database(this);
            List<Product> products = database.getProductbyIdCategory(idCategory);
            int numberColumn = 2;
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,  numberColumn);
            recyclerView.setLayoutManager(layoutManager);
            ProductAdapter productAdapter = new ProductAdapter(products,this );
            recyclerView.setAdapter(productAdapter);
        }
    }

    private void setInit() {
        recyclerView = findViewById(R.id.recycler_view);
        btnHighToLow = findViewById(R.id.btn_priceHigh);
        btnLowToHigh = findViewById(R.id.btn_priceLow);
    }
}