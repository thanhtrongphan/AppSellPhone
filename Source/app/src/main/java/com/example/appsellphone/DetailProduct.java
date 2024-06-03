package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import function.Database;
import function.SessionManager;
import model.Product;

public class DetailProduct extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView titleTxt, descriptionTxt, priceTxt;
    private ImageView picItem, backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        initView();
        Intent intent = getIntent();
        // get product intent.putExtra("object", product);
        Product product = (Product) intent.getSerializableExtra("object");
        setDetail(product);
        setButtonAdd(product);
    }

    private void setButtonAdd(Product product) {
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(DetailProduct.this);
                String sessionId = sessionManager.getSessionId();
                int productId = product.getId();
                if(checkHaveIteminCart(sessionId, productId)){
                    // update quantity
                    Database db = new Database(DetailProduct.this);
                    db.updateQuantity(sessionId, productId);
                    Toast.makeText(DetailProduct.this, "Add to cart successfully", Toast.LENGTH_SHORT).show();
                }else{
                    // add new item
                    Database db = new Database(DetailProduct.this);
                    db.addToCart(sessionId, productId);
                    Toast.makeText(DetailProduct.this, "Add to cart successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkHaveIteminCart(String sessionId, int productId) {
        Database db = new Database(this);
        return db.checkHaveIteminCart(sessionId, productId);
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        picItem = findViewById(R.id.itemPic);
        priceTxt = findViewById(R.id.priceTxt);
        backBtn = findViewById(R.id.backBtn);
    }
    public void setDetail(Product product) {
        titleTxt.setText(product.getName());
        descriptionTxt.setText(product.getDetail());
        priceTxt.setText(product.getPrice());
        Picasso.get().load(product.getImage()).into(picItem);
        backBtn.setOnClickListener(v -> {
            finish();
        });
        addToCartBtn.setOnClickListener(v -> {
            // add to cart
        });
    }
}