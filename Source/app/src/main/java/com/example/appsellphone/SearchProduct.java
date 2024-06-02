package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import adapters.ProductAdapter;
import model.Product;

public class SearchProduct extends AppCompatActivity {
    RecyclerView searchProductRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        setInit();
        List<Product> products = (List<Product>) getIntent().getSerializableExtra("products");
        setProduct(products);
    }

    private void setProduct(List<Product> products) {
        ProductAdapter productAdapter = new ProductAdapter(products,this );
        searchProductRecyclerView.setAdapter(productAdapter);
        searchProductRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setInit() {
        searchProductRecyclerView = findViewById(R.id.recyclerView_search);
    }
}