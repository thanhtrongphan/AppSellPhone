package com.example.appsellphone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import function.Database;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email;
    Button btnRegister;
    TextView btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setInit();
        btnRegister.setOnClickListener(v -> {
            register();
        });
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }
    public void register(){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String mail = email.getText().toString();
        if(checkAccountIsExist(user, mail)){
            // Nếu tài khoản đã tồn tại
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Lỗi đăng ký");
            builder.setMessage("Tài khoản hoặc email đã tồn tại.");
            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    username.setText("");
                    password.setText("");
                    email.setText("");
                }
            });
            builder.create().show();
        } else {
            createDatabase(user, pass, mail);
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void createDatabase(String username, String password, String email) {
        Database database = new Database(this);
        database.createAccount(username, password, email);
    }

    public boolean checkAccountIsExist(String user, String mail) {
        Database database = new Database(this);
        if (database.checkAccountIsExist(user, mail)) {
            return true;
        }
        return false;
    }
    private void setInit() {
        username = findViewById(R.id.edt_fullname);
        password = findViewById(R.id.edt_password);
        email = findViewById(R.id.edt_email);
        btnRegister = findViewById(R.id.bnt_register);
        btnLogin = findViewById(R.id.text_signin);
    }
}