package com.example.proyecto.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.Entidades.Theme;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterTheme extends RecyclerView.Adapter<AdapterTheme.SessionHolder> {

    private Context context;
    private ArrayList<Theme> arrayList;
    private Integer size;

    public AdapterTheme() {
    }

    public AdapterTheme(Context context, ArrayList<Theme> arrayList, Integer size) {
        this.context = context;
        this.arrayList = arrayList;
        this.size = size;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterTheme.SessionHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);

        return new SessionHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SessionHolder holder, int position) {
        Picasso.get().load(arrayList.get(position).getImg())
                .resize(250,250)
                .into(holder.imageView);
//        holder.txvSess.setText(arrayList.get(position).getSesDesc());
        holder.txvDesc.setText(arrayList.get(position).getImgDesc());
        holder.txvTit.setText(arrayList.get(position).getImgTit());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SessionHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txvSess,txvTit,txvDesc;

        public SessionHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imv_item_theme);
//            txvSess = itemView.findViewById(R.id.txv_item_theme_session);
            txvTit = itemView.findViewById(R.id.txv_item_theme_tit);
            txvDesc = itemView.findViewById(R.id.txv_item_theme_desc);
        }
    }
}
