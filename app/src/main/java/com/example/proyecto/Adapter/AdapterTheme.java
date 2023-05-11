package com.example.proyecto.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.Entidades.Theme;
import com.squareup.picasso.Picasso;

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
            view=layoutInflater.inflate(R.layout.item_theme,null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.imv_item_theme);
        TextView txvSess = (TextView) view.findViewById(R.id.txv_item_theme_session);
        TextView txvTit = (TextView) view.findViewById(R.id.txv_item_theme_tit);
        TextView txvDesc = (TextView) view.findViewById(R.id.txv_item_theme_desc);
        Picasso.get().load(arrayList.get(position).getImg()).into(imageView);
        txvSess.setText(arrayList.get(position).getSesDesc());
        txvDesc.setText(arrayList.get(position).getImgDesc());
        txvTit.setText(arrayList.get(position).getImgTit());

        return view;
    }
}
