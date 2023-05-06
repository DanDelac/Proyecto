package com.example.proyecto.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Entidades.Theme;
import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.Entidades._Session;
import com.example.proyecto.R;
import com.example.proyecto.ui.Theme.DetailTheme;
import com.example.proyecto.ui.editaccount.editAccount;
import com.example.proyecto.ui.home.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterUnit extends RecyclerView.Adapter<AdapterUnit.SessionHolder>{

    List<Unit> lista_Unit;
    ArrayList<_Session> _sessions;
    Context context;
    private View.OnClickListener listener;
    private static final String ID_CALULAR_GUARDADO = "ID";
    public static final String SESS_PREF="session";

    public AdapterUnit(Context context,List<Unit> lista_Unit, ArrayList<_Session> lista_Ses) {
        this.context=context;
        this.lista_Unit = lista_Unit;
        this._sessions = lista_Ses;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterUnit.SessionHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new SessionHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterUnit.SessionHolder holder, int position) {

        holder.txt_Unit.setText(lista_Unit.get(position).getUniDesc());
        holder.lst_Session.setAdapter(new ArrayAdapter<_Session>(holder.itemView.getContext(), android.R.layout.simple_list_item_1,_sessions));
        AdapterSession adapterSession = new AdapterSession(_sessions,context,lista_Unit.get(position).getIdUnit());
        holder.lst_Session.setAdapter(adapterSession);
        holder.lst_Session.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer idSes=_sessions.get(position).getIdSes();
                SharedPreferences log = context.getSharedPreferences(SESS_PREF,0);
                SharedPreferences.Editor editor = log.edit();
                editor.putString("idSes",idSes.toString());
                editor.commit();
                Intent i = new Intent(context, DetailTheme.class);
                holder.itemView.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return lista_Unit.size();
    }

    public static class SessionHolder extends RecyclerView.ViewHolder {

        TextView txt_Unit;
        ListView lst_Session;

        public SessionHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt_Unit = itemView.findViewById(R.id.txt_Unit);
            lst_Session = itemView.findViewById(R.id.lst_Session);

        }
    }
}
