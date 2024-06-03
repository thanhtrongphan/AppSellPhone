package adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsellphone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import function.Database;
import model.CartItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    List<CartItem> cartItemList;
    Context context;

    public CartAdapter(List<CartItem> cartItemList, Context context) {
        this.cartItemList = cartItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_view, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(cartItemList.get(position).getTitle());
        holder.feeEachItem.setText(cartItemList.get(position).getPrice() + "VND");
        Double totaleach = cartItemList.get(position).getNumberinCart() * cartItemList.get(position).getPrice();
        holder.totalEachItem.setText( Math.round((cartItemList.get(position).getNumberinCart() * cartItemList.get(position).getPrice())) + "VND");
        holder.num.setText(String.valueOf(cartItemList.get(position).getNumberinCart()));
        Picasso.get().load(cartItemList.get(position).getPicUrl()).into(holder.pic);
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItemList.get(position).setNumberinCart(cartItemList.get(position).getNumberinCart() + 1);
                holder.num.setText(String.valueOf(cartItemList.get(position).getNumberinCart()));
                holder.totalEachItem.setText( Math.round((cartItemList.get(position).getNumberinCart() * cartItemList.get(position).getPrice())) + "VND");
                updateCart(cartItemList.get(position).getId(), cartItemList.get(position).getNumberinCart());
                if (onTotalPriceChangeListener != null) {
                    double totalPrice = calculateTotalPrice();
                    onTotalPriceChangeListener.onTotalPriceChange(totalPrice);
                }
            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartItemList.get(position).getNumberinCart() > 1){
                    cartItemList.get(position).setNumberinCart(cartItemList.get(position).getNumberinCart() - 1);
                    holder.num.setText(String.valueOf(cartItemList.get(position).getNumberinCart()));
                    holder.totalEachItem.setText( Math.round((cartItemList.get(position).getNumberinCart() * cartItemList.get(position).getPrice())) + "VND");
                    updateCart(cartItemList.get(position).getId(), cartItemList.get(position).getNumberinCart());

                }
                else{
                    Database database = new Database(context);
                    database.deleteCart(cartItemList.get(position).getId());
                    cartItemList.remove(position);
                    notifyDataSetChanged();
                }
                if (onTotalPriceChangeListener != null) {
                    double totalPrice = calculateTotalPrice();
                    onTotalPriceChangeListener.onTotalPriceChange(totalPrice);
                }
            }
        });

    }
    public interface OnTotalPriceChangeListener {
        void onTotalPriceChange(double totalPrice);
    }

    private OnTotalPriceChangeListener onTotalPriceChangeListener;

    public void setOnTotalPriceChangeListener(OnTotalPriceChangeListener listener) {
        this.onTotalPriceChangeListener = listener;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : cartItemList) {
            totalPrice += item.getPrice() * item.getNumberinCart();
        }
        return totalPrice;
    }
    private void updateCart(int id, int numberinCart) {
        Database database = new Database(context);
        database.updateCart(id, numberinCart);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView pic;
        TextView totalEachItem, num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.pludCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }

}
