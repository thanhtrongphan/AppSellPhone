package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsellphone.CategoryProduct;
import com.example.appsellphone.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Category;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{


    private Context context;
    private List<Category> categories;

    public MenuAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuViewHolder holder;
        View layout = LayoutInflater.from(context).inflate(R.layout.item_menu_view, parent, false);
        holder = new MenuViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Category category = categories.get(position);
        // set src for imageButton
        holder.btn.setText(category.getName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCategory = category.getId();
                Intent intent = new Intent(context, CategoryProduct.class);
                intent.putExtra("idCategory", idCategory);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        Button btn;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.bt_item_menu);
        }
    }
}
