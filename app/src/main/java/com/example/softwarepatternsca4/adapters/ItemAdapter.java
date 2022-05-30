package com.example.softwarepatternsca4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softwarepatternsca4.R;
import com.example.softwarepatternsca4.models.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<Item> ItemModelList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(ItemModelList.get(position).getTitle()).into(holder.img);
        holder.name.setText(ItemModelList.get(position).getTitle());
        holder.desc.setText(ItemModelList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return ItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, desc, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.itemimg);
            name = itemView.findViewById(R.id.itemname);
            desc = itemView.findViewById(R.id.itemdesc);

        }
    }
}
