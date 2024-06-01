package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



import function.Database;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create database from class Database
        Database database = new Database(this);
        Intent intent = new Intent(MainActivity.this, ShowProductActivity.class);
        startActivity(intent);

    }
}