package com.example.proyecto.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.Entidades.Glosary_listElement;
import com.example.proyecto.R;

import java.util.List;

public class AdapterGlosary extends RecyclerView.Adapter<AdapterGlosary.gloHolder> {

    int[] icons = {R.drawable.abecedario,R.drawable.pagweb,R.drawable.mas};
    private List<Glosary_listElement> mData;
    private LayoutInflater mInflater;
    private Context context;

    public AdapterGlosary(Context context, List<Glosary_listElement> itemList){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @NonNull
    @Override
    public AdapterGlosary.gloHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.glosary_element, null);
        return new AdapterGlosary.gloHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull gloHolder gloHolder, int i) {
        gloHolder.bindData(mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItems(List<Glosary_listElement> items) {mData = items;}

    public class gloHolder extends RecyclerView.ViewHolder {
        ImageView iconImg;
        TextView title,description;

        public gloHolder(@NonNull View itemView) {
            super(itemView);
            iconImg = itemView.findViewById(R.id.imgv_icon);
            title = itemView.findViewById(R.id.txt_ItemName);
            description = itemView.findViewById(R.id.txt_Itemdescrip);
        }

        void bindData(final Glosary_listElement item){
           iconImg.setImageResource(icons.length);
           title.setText(item.getTitle());
           description.setText(item.getDescription());
        }
    }
}
