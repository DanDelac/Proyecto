package com.example.proyecto.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.Entidades.Theme;

import java.util.ArrayList;

public class AdapterTheme extends BaseAdapter {
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
            view=layoutInflater.inflate(R.layout.activity_theme,null);
        }
        TextView theSess = (TextView) view.findViewById(R.id.txv_theme_sess);
        TextView theDesc = (TextView) view.findViewById(R.id.txv_theme_desc);


        theSess.setText(arrayList.get(position).getSesDesc());
        theDesc.setText(arrayList.get(position).getTheDesc());

        theSess.setTextSize(size);
        theDesc.setTextSize(size);

        return view;
    }
}
