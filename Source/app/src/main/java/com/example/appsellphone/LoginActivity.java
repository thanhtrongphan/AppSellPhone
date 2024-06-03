package com.example.appsellphone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import function.Database;
import function.SessionManager;

public class LoginActivity extends AppCompatActivity {

    TextView txtregister;
    EditText user;
    EditText pass;
    Button bntsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setInit();


        bntsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                if(checkHaveAccount(username,password)){
                    SessionManager session = new SessionManager(LoginActivity.this);
                    int idUser = getIdUser(username);
                    session.saveUserId(idUser + "");
                    int sessionId = getSessionId(idUser + "");
                    session.saveSessionId(sessionId + "");
                    Intent intent = new Intent(LoginActivity.this, ShowProductActivity.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Lỗi đăng nhập");
                    builder.setMessage("Tên tài khoản hoặc mật khẩu sai.");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                }
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
    public int getSessionId(String idUser){
        Database db = new Database(this);
        return db.getSessionID(idUser);
    }
    public int getIdUser(String username){
        Database db = new Database(this);
        return db.getAccountId(username);
    }
    private boolean checkHaveAccount(String username, String password){
        Database db = new Database(this);
        if(db.checkAccount(username,password)){
            return true;
        }
        return false;

    }
    private void setInit() {
        bntsignin =findViewById(R.id.bnt_signin);
        txtregister = (TextView) findViewById(R.id.txt_register);
        user = findViewById(R.id.edt_fullname);
        pass = findViewById(R.id.edt_pass);
    }
}