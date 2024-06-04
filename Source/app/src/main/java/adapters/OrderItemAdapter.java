package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsellphone.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import model.OrderItem;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    List<OrderItem> orderItemList;
    Context context;

    public OrderItemAdapter(List<OrderItem> orderItemList, Context context) {
        this.orderItemList = orderItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(View.inflate(context, R.layout.item_order_detail_view, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);
        holder.tvName.setText(orderItem.getName());
        Picasso.get().load(orderItem.getUrlImage()).into(holder.imgProduct);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        Double each = Double.valueOf(orderItem.getPrice());
        String eachPrice = decimalFormat.format(each);
        holder.tvPrice.setText(eachPrice + " VND");
        Double total = Double.valueOf(orderItem.getTotal());
        String totalPrice = decimalFormat.format(total);
        holder.tvTotal.setText(totalPrice + " VND");
        holder.tvQuantity.setText(orderItem.getNumber() + "");

    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice,tvTotal, tvQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.pic);
            tvName = itemView.findViewById(R.id.titleTxt);
            tvPrice = itemView.findViewById(R.id.feeEachItem);
            tvTotal = itemView.findViewById(R.id.totalEachItem);
            tvQuantity = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
