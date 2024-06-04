package com.example.appsellphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import adapters.CartAdapter;
import function.Database;
import function.SessionManager;
import model.CartItem;

public class Cart extends AppCompatActivity implements CartAdapter.OnTotalPriceChangeListener{
    RecyclerView recyclerView;
    ImageView btnBack;
    TextView totalCart;
    EditText edtAddress, edtPhone;
    AppCompatButton btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setInit();
        setBtnBack();
        setRecycler();
        setBtnOrder();
    }

    private void setBtnOrder() {
        btnOrder.setOnClickListener(v -> {
            Database db = new Database(this);
            SessionManager sessionManager = new SessionManager(this);
            String SessionID = sessionManager.getSessionId();
            String address = edtAddress.getText().toString();
            String phone = edtPhone.getText().toString();
            if (address.isEmpty() || phone.isEmpty()){
                Toast.makeText(this, "Xin nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!db.checkHaveItem(SessionID))
            {
                Toast.makeText(this, "Giỏ hàng không có sản phẩm", Toast.LENGTH_SHORT).show();
                return;
            }
            String AccountID = sessionManager.getUserId();
            db.order(AccountID, SessionID, address, phone);
            Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void setBtnBack() {
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void setRecycler() {
        List<CartItem> list = getListCart();
        CartAdapter adapter = new CartAdapter(list, this);
        adapter.setOnTotalPriceChangeListener(this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        double totalPrice = adapter.calculateTotalPrice();
        onTotalPriceChange(totalPrice);
    }
    @Override
    public void onTotalPriceChange(double totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTotalPrice = decimalFormat.format(totalPrice);
        totalCart.setText(formattedTotalPrice + " VND");
    }
    private void setInit() {
        recyclerView = findViewById(R.id.recycler_cart);
        btnBack = findViewById(R.id.backBtn);
        totalCart = findViewById(R.id.tv_total_cart);
        edtAddress = findViewById(R.id.edt_address);
        edtPhone = findViewById(R.id.edt_phone_cart);
        btnOrder = findViewById(R.id.button_order_cart);
    }
    private List<CartItem> getListCart(){
        Database db = new Database(this);
        SessionManager sessionManager = new SessionManager(this);
        String SessionID = sessionManager.getSessionId();
        return db.getCartItems(SessionID);
    }
}