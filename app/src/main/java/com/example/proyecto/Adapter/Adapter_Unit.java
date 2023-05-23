package com.example.proyecto.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.Entidades._Session;
import com.example.proyecto.R;
import com.example.proyecto.ui.Theme.DetailTheme;
import com.example.proyecto.ui.evaluation.Evaluation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Unit extends RecyclerView.Adapter<Adapter_Unit.SessionHolder>{

    List<Unit> lista_Unit;
    ArrayList<_Session> _sessions;

    Context context;
    private View.OnClickListener listener;
    private static final String ID_CALULAR_GUARDADO = "ID";
    public static final String SESS_PREF="session";
    public static final String UNIT_PREF="unit";

    public Adapter_Unit(Context context, List<Unit> lista_Unit, ArrayList<_Session> lista_Ses) {
        this.context=context;
        this.lista_Unit = lista_Unit;
        this._sessions = lista_Ses;
    }

    @NonNull
    @NotNull
    @Override
    public Adapter_Unit.SessionHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_2, parent, false);

        return new SessionHolder(vista);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_Unit.SessionHolder holder, int position) {

        ArrayList<String> list = nueva_lista(_sessions,lista_Unit.get(position).getIdUnit());
        holder.txt_Unit_2.setText(lista_Unit.get(position).getUniDesc());
        Integer aux = (Integer.parseInt(list.get(2))+Integer.parseInt(list.get(5))+Integer.parseInt(list.get(8))+Integer.parseInt(list.get(11)))/4;
        holder.pb_U.setProgress(aux);
//        holder.lst_Session.setAdapter(new ArrayAdapter<_Session>(holder.itemView.getContext(), android.R.layout.simple_list_item_1,_sessions));


        holder.txt_T1.setText(list.get(0));
        holder.txt_S1.setText(list.get(1));
        holder.pb_S1.setProgress(Integer.parseInt(list.get(2)));
        holder.txt_T2.setText(list.get(3));
        holder.txt_S2.setText(list.get(4));
        holder.pb_S2.setProgress(Integer.parseInt(list.get(5)));
        holder.txt_T3.setText(list.get(6));
        holder.txt_S3.setText(list.get(7));
        holder.pb_S3.setProgress(Integer.parseInt(list.get(8)));
        holder.txt_T4.setText(list.get(9));
        holder.txt_S4.setText(list.get(10));
        holder.pb_S4.setProgress(Integer.parseInt(list.get(11)));

        holder.txt_T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goThem(list.get(1),list.get(0),list.get(2),lista_Unit.get(position).getIdUseUni(),2,holder);
            }
        });
        holder.txt_T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goThem(list.get(4),list.get(3),list.get(5),lista_Unit.get(position).getIdUseUni(),2,holder);
            }
        });
        holder.txt_T3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goThem(list.get(7),list.get(6),list.get(8),lista_Unit.get(position).getIdUseUni(),2,holder);
            }
        });
        holder.txt_T4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goThem(list.get(10),list.get(9),list.get(11),lista_Unit.get(position).getIdUseUni(),2,holder);
            }
        });

        holder.btn_eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod=lista_Unit.get(position).getIdUnit().toString();
                String unit=lista_Unit.get(position).getUniDesc();
                goEval(v,cod,unit);
            }
        });

//        holder.progressBar1.setMax(55);
//        holder.progressBar1.setProgress(50);

    }

    private void goEval(View view,String cod,String unit) {
        SharedPreferences log = context.getSharedPreferences(UNIT_PREF,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putString("idUnit",cod);
        editor.putString("sesTit",unit);
        editor.commit();
        Intent intent = new Intent(context, Evaluation.class);
        view.getContext().startActivity(intent);
        Toast.makeText(context, "Te dije que le des al boton ctmr... XD", Toast.LENGTH_SHORT).show();
    }

    private void goThem(String idSes, String sesTit, String sesP,Integer idUseUni,Integer sesD,@NonNull @NotNull Adapter_Unit.SessionHolder view) {

        SharedPreferences log = context.getSharedPreferences(SESS_PREF,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putString("idSes",idSes);
        editor.putString("sesTit",sesTit);
        editor.putString("sesP",sesP);
        editor.putString("idUseUni",idUseUni.toString());
        editor.commit();
        Intent intent = new Intent(context, DetailTheme.class);
        view.itemView.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return lista_Unit.size();
    }

    public static class SessionHolder extends RecyclerView.ViewHolder {

        TextView txt_Unit_2,txt_T1,txt_T2,txt_T3,txt_T4, txt_S1, txt_S2, txt_S3, txt_S4;
        ProgressBar pb_S1, pb_S2, pb_S3, pb_S4,pb_U;
        Button btn_eval;

        LinearLayout linearLayout;

        public SessionHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt_Unit_2 = itemView.findViewById(R.id.txt_Unit_2);
            txt_T1 = itemView.findViewById(R.id.txt_T1);
            txt_T2 = itemView.findViewById(R.id.txt_T2);
            txt_T3 = itemView.findViewById(R.id.txt_T3);
            txt_T4 = itemView.findViewById(R.id.txt_T4);
            txt_S1 = itemView.findViewById(R.id.txt_Ses1);
            txt_S2 = itemView.findViewById(R.id.txt_Ses2);
            txt_S3 = itemView.findViewById(R.id.txt_Ses3);
            txt_S4 = itemView.findViewById(R.id.txt_Ses4);
            pb_U = itemView.findViewById(R.id.progress_Bar_Uni);
            pb_S1 = itemView.findViewById(R.id.progress_Bar_Ses1);
            pb_S2 = itemView.findViewById(R.id.progress_Bar_Ses2);
            pb_S3 = itemView.findViewById(R.id.progress_Bar_Ses3);
            pb_S4 = itemView.findViewById(R.id.progress_Bar_Ses4);
            btn_eval = itemView.findViewById(R.id.btn_item2_eval);
//            progressBar1 = itemView.findViewById(R.id.progress_Bar1);
        }
    }

    private ArrayList<String> nueva_lista(ArrayList<_Session> lista_Ses, Integer id) {

        ArrayList<_Session> sessions_Unit;
        sessions_Unit = lista_Ses;
        ArrayList<String> stringList = new ArrayList<String>();
        Integer aux=0;

        for (int i=0;i<sessions_Unit.size(); i++){
            if(sessions_Unit.get(i).getIdUnit()==id){
                stringList.add(sessions_Unit.get(i).getSesDesc());
                stringList.add(sessions_Unit.get(i).getIdSes().toString());
                stringList.add(sessions_Unit.get(i).getSesPorc().toString());
            }
        }
        return stringList;
    }
}
