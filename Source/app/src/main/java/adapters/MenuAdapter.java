package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsellphone.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{


    private Context context;
    private ArrayList<HashMap<String,String>> items;
    public MenuAdapter(Context context, ArrayList<HashMap<String, String>> items) {
        this.context = context;
        this.items = items;
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
        HashMap<String,String> item = items.get(position);
        // set src for imageButton
        String imageName = item.get("image");
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.imageButton.setImageResource(resId);
        String nameText = item.get("name");
        holder.textView.setText(nameText);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView textView;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.showProduct_itemMenu_imageButton);
            textView = itemView.findViewById(R.id.showProduct_itemMenu_textView);
        }
    }
}
