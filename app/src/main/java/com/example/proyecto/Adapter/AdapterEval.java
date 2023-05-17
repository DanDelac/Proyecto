package com.example.proyecto.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.proyecto.Entidades.Theme;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterEval extends RecyclerView.Adapter<AdapterTheme.SessionHolder> {

    private Context context;
    private ArrayList<Theme> arrayList;
    private Integer size;

    public AdapterEval() {
    }

    @NonNull
    @NotNull
    @Override
    public AdapterTheme.SessionHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterTheme.SessionHolder sessionHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public AdapterEval(Context context, ArrayList<Theme> arrayList, Integer size) {
        this.context = context;
        this.arrayList = arrayList;
        this.size = size;
    }
}
