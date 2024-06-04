package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsellphone.OrderDetailActivity;
import com.example.appsellphone.R;

import java.text.DecimalFormat;
import java.util.List;

import model.Order;

public class OrderAdapter  extends  RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    List<Order> orderList;
    Context context;

    public OrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder;
        View layout = LayoutInflater.from(context).inflate(R.layout.item_order_view, parent, false);
        holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        Double total = Double.valueOf(order.getTotal());
        String formattedPrice = decimalFormat.format(total);
        holder.txtTotal.setText("Đơn hàng trị giá " + formattedPrice + " VND");
        holder.txtAddress.setText("Địa chỉ: " + order.getAddress());
        holder.txtPhone.setText("SDT: "+ order.getPhone());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int productID = order.getId();
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("orderID", productID);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout btnDetail;
        TextView txtTotal, txtAddress, txtPhone;
        public ViewHolder(View itemView) {
            super(itemView);
            btnDetail = itemView.findViewById(R.id.order_detail_btn);
            txtTotal = itemView.findViewById(R.id.price_tv);
            txtAddress = itemView.findViewById(R.id.address_tv);
            txtPhone = itemView.findViewById(R.id.phone_tv);
        }
    }
}
