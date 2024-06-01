package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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