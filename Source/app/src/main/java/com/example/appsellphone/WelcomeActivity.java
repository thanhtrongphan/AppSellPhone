package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import function.Database;
import function.SessionManager;

public class WelcomeActivity extends AppCompatActivity {
    Button button ;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Database database = new Database(this);
        database.getWritableDatabase();
        textView = findViewById(R.id.tv_sign_in);
        button = findViewById(R.id.bnt_dangki_welcome);
        checkIsLogin();
//        imageView = findViewById(R.id.imv_welcome);
//        // add android:src="@drawable/bg4" in picasso imageview
//        Picasso.get().load(R.drawable.bg4).into(imageView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkIsLogin() {
        SessionManager sessionManager = new SessionManager(this);
        if(sessionManager.getUserId() != null){
            if(sessionManager.getSessionId() == null){
                String userID = sessionManager.getUserId();
                checkSessionID(userID);
            }
            Intent intent = new Intent(WelcomeActivity.this , ShowProductActivity.class);
            startActivity(intent);
        }
    }

    private void checkSessionID(String userID) {
        Database database = new Database(this);
        int sessionID = database.getSessionID(userID);
        SessionManager sessionManager = new SessionManager(this);
        sessionManager.saveSessionId(String.valueOf(sessionID));
    }
}