package com.example.proyecto.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.data.model.Theme;
import com.example.proyecto.databinding.ItemThemesliderBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

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
        holder.binding.txtTit.setText(theme.getImgTit());
        holder.binding.gif1.setImageResource(holder.gifIds[position]);
    }

    @Override
    public int getItemCount() {
        return List_Theme.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ItemThemesliderBinding binding;
        Integer[] gifIds  = new Integer[] {R.drawable.gif_a,R.drawable.gif_b,R.drawable.gif_c,R.drawable.gif_d,
                R.drawable.gif_e,R.drawable.gif_f,R.drawable.gif_g,R.drawable.gif_h,R.drawable.gif_i,R.drawable.gif_j
                ,R.drawable.gif_k,R.drawable.gif_l,R.drawable.gif_m,R.drawable.gif_n,R.drawable.gif_n2,R.drawable.gif_o
                ,R.drawable.gif_p,R.drawable.gif_q,R.drawable.gif_r,R.drawable.gif_s,R.drawable.gif_t,R.drawable.gif_u
                ,R.drawable.gif_v,R.drawable.gif_w,R.drawable.gif_x,R.drawable.gif_y,R.drawable.gif_z};
        public ImageViewHolder(ItemThemesliderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
