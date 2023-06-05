package com.example.proyecto.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.R;
import com.example.proyecto.Entidades.Theme;
import com.example.proyecto.databinding.ItemThemeBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterTheme extends RecyclerView.Adapter<AdapterTheme.ThemeHolder> {

    private Context context;
    private ArrayList<Theme> List_Theme;

    public AdapterTheme(Context context, ArrayList<Theme> List_Theme) {
        this.context = context;
        this.List_Theme = List_Theme;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterTheme.ThemeHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        View vista =
//                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);
//        return new ThemeHolder(vista);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemThemeBinding binding = ItemThemeBinding.inflate(inflater, parent, false);
        return new ThemeHolder(binding);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterTheme.ThemeHolder holder, int position) {
        Theme theme = List_Theme.get(position);
        Picasso.get().load(theme.getImg())
                .resize(200,250)
                .into(holder.binding.imvItemTheme);
//        holder.txvSess.setText(arrayList.get(position).getSesDesc());
        String desc= theme.getImgDesc();
        if(desc.isEmpty())
//            holder.txvDesc.setText("No hay descripción");
            holder.binding.txvItemThemeDesc.setVisibility(View.GONE);
        else{
            holder.binding.txvItemThemeDesc.setText("[Ver descripción...]");
            holder.binding.txvItemThemeDesc.setTextColor(Color.BLUE);
            holder.binding.txvItemThemeDesc.setVisibility(View.VISIBLE);
        }
//        holder.txvDesc.setText(arrayList.get(position).getImgDesc());
        holder.binding.txvItemThemeTit.setText(theme.getImgTit());

        holder.binding.txvItemThemeDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogDesc( position);
            }
        });
    }

    private void ShowDialogDesc(int position) {
        Theme theme = List_Theme.get(position);
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_5);
//        LinearLayout ll = dialog.findViewById(R.id.ll_theme);
        ImageView imv = dialog.findViewById(R.id.dialog_img);
        TextView tit = dialog.findViewById(R.id.dialog_tit);
        TextView desc = dialog.findViewById(R.id.dialog_desc);

        Picasso.get().load(List_Theme.get(position).getImg())
                .into(imv);
        tit.setText(theme.getImgTit());
        desc.setVisibility(View.VISIBLE);
        desc.setText(theme.getImgDesc());

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return List_Theme.size();
    }

    public class ThemeHolder extends RecyclerView.ViewHolder {
        ItemThemeBinding binding;
        public ThemeHolder(@NonNull @NotNull ItemThemeBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
