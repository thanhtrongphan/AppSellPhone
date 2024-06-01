package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsellphone.R;

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
        // set image for imageView
        String image = product.getImage();

        holder.textView.setText(product.getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to detail activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imv_item_product_image);
            textView = itemView.findViewById(R.id.tv_item_product_name);
            button = itemView.findViewById(R.id.btn_item_product_detail);
        }
    }
}
