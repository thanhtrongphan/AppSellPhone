package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsellphone.DetailProduct;
import com.example.appsellphone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    List<Product> products;
    Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder;
        View layout = View.inflate(context, R.layout.item_product_view, null);
        holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.titleTxt.setText(product.getName());
        String url = product.getImage();
        Picasso.get().load(url).into(holder.pic);
        holder.price.setText(product.getPrice() + " VND");
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailProduct.class);
            intent.putExtra("object", product);
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            price = itemView.findViewById(R.id.feeTxt);
        }
    }
}
