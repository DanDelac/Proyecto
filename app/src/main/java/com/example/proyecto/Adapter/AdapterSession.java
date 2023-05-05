package com.example.proyecto.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.Entidades.Unit_Session;
import com.example.proyecto.Entidades._Session;
import com.example.proyecto.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterSession extends RecyclerView.Adapter<AdapterSession.CelularesHolder>{

    List<Unit> lista_Unit;
    List<_Session> _sessions;
    private View.OnClickListener listener;
    private static final String ID_CALULAR_GUARDADO = "ID";

    public AdapterSession(List<Unit> lista_Unit,List<_Session> lista_Ses) {
        this.lista_Unit = lista_Unit;
        this._sessions = lista_Ses;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterSession.CelularesHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new AdapterSession.CelularesHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterSession.CelularesHolder holder, int position) {

        holder.txt_Unit.setText(lista_Unit.get(position).getUniDesc());

//        List<String> dataList = new ArrayList<>();
//        dataList.add("Dato 1");
//        dataList.add("Dato 2");
//        dataList.add("Dato 3");
//
//        // Crea una instancia del adaptador personalizado y establece la lista de datos en el adaptador
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
//
//        holder.lst_Session.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return lista_Unit.size();
    }

    public static class CelularesHolder extends RecyclerView.ViewHolder {

        TextView txt_Unit;
        ListView lst_Session;

        public CelularesHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt_Unit = itemView.findViewById(R.id.txt_Unit);
            lst_Session = itemView.findViewById(R.id.lst_Session);

        }
    }
}
