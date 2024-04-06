package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import adapters.MenuAdapter;

public class ShowProductActivity extends AppCompatActivity {
    RecyclerView menuRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        menuRecyclerView = findViewById(R.id.ShowPrduct_MenuRecyclerView);
        ArrayList<HashMap<String,String>> items = new ArrayList<>();
        HashMap<String,String> item = new HashMap<>();
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        item.put("image","ic_shoppingcart");
        item.put("name","Product 1");
        items.add(item);
        MenuAdapter adapter = new MenuAdapter(this,items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        menuRecyclerView.setLayoutManager(layoutManager);
        menuRecyclerView.setAdapter(adapter);

    }
}