package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.List;

import adapters.OrderItemAdapter;
import function.Database;
import model.OrderItem;

public class OrderDetailActivity extends AppCompatActivity {
    ImageView backBtn;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setInit();
        int orderID = getIntent().getIntExtra("orderID", 0);
        List<OrderItem> orderItemList = getListOrderItem(orderID);
        setRecycler(orderItemList);
        setBack();
    }

    private void setBack() {
        backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void setRecycler(List<OrderItem> orderItemList) {
        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(orderItemList, this);
        recyclerView.setAdapter(orderItemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private List<OrderItem> getListOrderItem(int orderID) {
        Database database = new Database(this);
        return database.getListOrderItem(orderID);
    }


    private void setInit() {
        backBtn = findViewById(R.id.backBtn);
        recyclerView = findViewById(R.id.orderDetailRecyclerView);
    }
}