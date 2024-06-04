package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import adapters.MenuAdapter;
import adapters.ProductAdapter;
import function.Database;
import function.SessionManager;
import model.Category;
import model.Product;

public class ShowProductActivity extends AppCompatActivity {
    RecyclerView menuRecyclerView;
    TextView seeAllProduct;
    RecyclerView productRecyclerView;
    EditText searchProduct;
    TextView username;
    LinearLayout btnHome, btnCart, btnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        setInit();
        // get from database and set to categoriesName
        setMenu();
        setProduct();
        // set name
        setWelcome();
        // btn see all
        setSeeAll();
        // edit search
        setSearchEddit();
        // set btn home
        setBtnHome();
        // set btn cart
        setBtnCart();
        // set btn profile
        setBtnProfile();

    }

    private void setBtnProfile() {
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowProductActivity.this, InfomationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setBtnCart() {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowProductActivity.this, Cart.class);
                startActivity(intent);
            }
        });
    }

    private void setBtnHome(){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowProductActivity.this, ShowProductActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setWelcome(){
        SessionManager sessionManager = new SessionManager(this);
        String userId = sessionManager.getUserId();
        String name = getName(userId);
        username.setText(name);
    }
    private void setSeeAll(){
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
    private void setSearchEddit(){
        searchProduct.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Database database = new Database(ShowProductActivity.this);
                    List<Product> products = database.searchProduct(searchProduct.getText().toString());
                    Intent intent = new Intent(ShowProductActivity.this, SearchProduct.class);
                    intent.putExtra("products", (java.io.Serializable) products);
                    startActivity(intent);
                }
            }
        });
    }
    private String getName(String userID) {
        Database database = new Database(this);
        String name = database.getName(userID);
        return name;
    }
    private void setInit() {
        menuRecyclerView = findViewById(R.id.recycler_menu_show_product);
        productRecyclerView = findViewById(R.id.recycler_product_show_product);
        seeAllProduct = findViewById(R.id.SeeAll_show_product);
        searchProduct = findViewById(R.id.edit_search_show_product);
        username = findViewById(R.id.tv_name_user_show_product);
        btnHome = findViewById(R.id.homeBtn);
        btnCart = findViewById(R.id.cartBtn);
        btnProfile = findViewById(R.id.profileBtn);
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

