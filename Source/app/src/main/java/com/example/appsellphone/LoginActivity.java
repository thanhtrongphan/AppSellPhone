package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView txtregister;
    EditText user;
    EditText pass;
    Button bntsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bntsignin =findViewById(R.id.bnt_signin);
        txtregister = (TextView) findViewById(R.id.txt_register);
        user = findViewById(R.id.edt_fullname);
        pass = findViewById(R.id.edt_pass);

        bntsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });
        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}