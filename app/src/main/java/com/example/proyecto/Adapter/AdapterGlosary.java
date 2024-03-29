package com.example.proyecto.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.MainActivity;
import com.example.proyecto.data.model.Glosary;
import com.example.proyecto.R;
import com.example.proyecto.databinding.GlosaryElementBinding;
import com.example.proyecto.ui.view.Clasificador;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterGlosary extends RecyclerView.Adapter<AdapterGlosary.gloHolder>{
    Context context;
    ArrayList<Glosary> glosaries;

    public AdapterGlosary(Context context, ArrayList<Glosary> glosaries) {
        this.context = context;
        this.glosaries = glosaries;
    }

    public AdapterGlosary() {
    }

    @NonNull
    @Override
    public gloHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View vista =
//                LayoutInflater.from(parent.getContext()).inflate(R.layout.glosary_element, parent, false);
//        return new gloHolder(vista);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        GlosaryElementBinding binding = GlosaryElementBinding.inflate(inflater, parent, false);
        return new gloHolder(binding);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull gloHolder holder, int position) {
        Glosary glosary = glosaries.get(position);
        holder.binding.imgvIcon.setImageResource(glosary.getIcon());
        holder.binding.txtItemName.setText(glosary.getTitle());
        holder.binding.txtItemdescrip.setText(glosary.getDescription());
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        goClasifier();
                        break;
                    case 1:
                        goDialog(glosary.getTitle());
                        break;
                    case 2:
                    case 3:
                        goGoogle(glosary.getUrl());
                        break;
                    case 8:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        goYoutube(glosary.getUrl());
                        break;
                }
            }
        });
    }

    private void goClasifier() {
        context.startActivity(new Intent(context, Clasificador.class));
    }

    private void goDialog(String sesTit) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_6);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView imv_dashboard = dialog.findViewById(R.id.dialog_img);
        TextView the = dialog.findViewById(R.id.dialog_tit);
        Button btn = dialog.findViewById(R.id.btnBack);
        the.setText(sesTit);
        String url="https://concexion-base-datos.000webhostapp.com/imagenes/alfabeto.png";
        Picasso.get().load(url).into(imv_dashboard);
        dialog.findViewById(R.id.dialog_tit);
        dialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void goGoogle(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    private void goYoutube(String url) {
        String youtubeUrl = "https://www.youtube.com/watch?v="+url;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
        intent.setPackage("com.google.android.youtube");
// Verifica si la aplicación de YouTube está instalada en el dispositivo
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        } else {
            goGoogle(youtubeUrl);
        }

    }

    @Override
    public int getItemCount() {
        return glosaries.size();
    }

    public class gloHolder extends RecyclerView.ViewHolder {
        GlosaryElementBinding binding;
        CardView cv;
        public gloHolder(@NonNull GlosaryElementBinding binding) {
            super(binding.getRoot());
            cv = binding.getRoot().findViewById(R.id.cv);
            this.binding = binding;
        }
    }
}
