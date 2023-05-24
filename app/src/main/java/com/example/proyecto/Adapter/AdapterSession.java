package com.example.proyecto.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.Entidades._Session;
import com.example.proyecto.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterSession extends BaseAdapter {
    private ArrayList<String> arrayList;
    private Context context;
    public AdapterSession() {
    }

    public AdapterSession(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.item_session,null);
        }

        TextView txvSess = (TextView) view.findViewById(R.id.txt_itemS_Desc);
        txvSess.setText(arrayList.get(position));

        return view;
    }

    @Override
    public String toString() {
        return "Prueba";
    }
}
