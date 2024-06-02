package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import adapters.MenuAdapter;
import adapters.ProductAdapter;
import function.Database;
import model.Category;
import model.Product;

public class ShowProductActivity extends AppCompatActivity {
    RecyclerView menuRecyclerView;
    TextView seeAllProduct;
    RecyclerView productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        setInit();
        // get from database and set to categoriesName
        setMenu();
        setProduct();
        seeAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCategory = 0;
                Intent intent = new Intent(ShowProductActivity.this, CategoryProduct.class);
                intent.putExtra("idCategory", idCategory);
                startActivity(intent);
            }
        });

    }

    private void setInit() {
        menuRecyclerView = findViewById(R.id.recycler_menu_show_product);
        productRecyclerView = findViewById(R.id.recycler_product_show_product);
        seeAllProduct = findViewById(R.id.SeeAll_show_product);
    }

    public void setMenu() {
        Database database = new Database(this);
        List<Category> categories = database.getCategories();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        menuRecyclerView.setLayoutManager(linearLayoutManager);
        menuRecyclerView.setAdapter(new MenuAdapter(this, categories));
    }

    public void setProduct() {
        Database database = new Database(this);
        List<Product> products = database.getProducts();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        productRecyclerView.setLayoutManager(linearLayoutManager);
        productRecyclerView.setAdapter(new ProductAdapter(products, this));
    }
}

