package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import adapters.OrderAdapter;
import function.Database;
import function.SessionManager;
import model.Order;

public class InfomationActivity extends AppCompatActivity {
    ImageView imgBack;
    RecyclerView recyclerView;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        setInit();
        setLogout();
        List<Order> listOrder = getListOrder();
        setRecycler(listOrder);
        setBack();
    }

    private void setBack() {
        imgBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void setRecycler(List<Order> listOrder) {
        OrderAdapter orderAdapter = new OrderAdapter(listOrder, InfomationActivity.this);
        recyclerView.setAdapter(orderAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private List<Order> getListOrder() {
        SessionManager sessionManager = new SessionManager(InfomationActivity.this);
        String AccountID = sessionManager.getUserId();
        Database database = new Database(this);
        return database.getListOrder(AccountID);
    }

    private void setLogout() {
        btnLogout.setOnClickListener(v -> {
            SessionManager sessionManager = new SessionManager(InfomationActivity.this);
            sessionManager.clearSession();
            Intent intent = new Intent(InfomationActivity.this, WelcomeActivity.class);
            startActivity(intent);
        });
    }

    private void setInit() {
        imgBack = findViewById(R.id.backBtn);
        recyclerView = findViewById(R.id.recyclerView);
        btnLogout = findViewById(R.id.logoutBtn);
    }
}