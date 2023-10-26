package com.example.proyecto.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.data.model.Theme;
import com.example.proyecto.databinding.ItemThemesliderBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_ThemeSlider extends RecyclerView.Adapter<Adapter_ThemeSlider.ImageViewHolder>{
    private Context context;
    private int[] imageArray;

    private ArrayList<Theme> List_Theme;

    public Adapter_ThemeSlider(Context context,ArrayList<Theme> List_Theme) {
        this.context = context;
        this.List_Theme = List_Theme;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_themeslider, parent, false);
//        return new ImageViewHolder(view);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemThemesliderBinding binding = ItemThemesliderBinding.inflate(inflater, parent, false);
        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
//        holder.imageView.setImageResource(imageArray[position]);
        Theme theme = List_Theme.get(position);
        Picasso.get().load(theme.getImg())
                .resize(200,250)
                .into(holder.binding.itemImageView);
//        holder.binding.itemImageView.setImageResource(imageArray[position]);
    }

    @Override
    public int getItemCount() {
        return List_Theme.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ItemThemesliderBinding binding;
        public ImageViewHolder(ItemThemesliderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
