package com.example.proyecto.Adapter;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.Util.New.Kotlinclass.ModelUnit;
import com.example.proyecto.Util.New.Kotlinclass.QuoteModelUnidadSesion;
import com.example.proyecto.databinding.ItemUnitBinding;
import com.example.proyecto.ui.Theme.DetailTheme;
import com.example.proyecto.ui.evaluation._Evaluacion;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Unit extends RecyclerView.Adapter<Adapter_Unit.UnitHolder>{
    List<ModelUnit> lista_Unit;
    ArrayList<QuoteModelUnidadSesion> List_UserSes;
    Context context;
    private static final String EVAL_SES = "ID";
    public static final String SESS_PREF="session";

    public Adapter_Unit(Context context, List<ModelUnit> lista_Unit, ArrayList<QuoteModelUnidadSesion> lista_Ses) {
        this.context=context;
        this.lista_Unit = lista_Unit;
        this.List_UserSes = lista_Ses;
    }

    @NonNull
    @NotNull
    @Override
    public Adapter_Unit.UnitHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUnitBinding binding = ItemUnitBinding.inflate(inflater, parent, false);
        return new UnitHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_Unit.UnitHolder holder, int position) {

        ModelUnit unit = lista_Unit.get(position);

//        ArrayList<String> list = nueva_lista(List_UserSes,unit.getIdUnit());
        holder.binding.txtUnit2.setText(unit.getUniDesc());

        String desSes = List_UserSes.get(4*position+0).getOSes().getSesDesc();
        holder.binding.txtT1.setText(desSes);//alfabeto ->DescripcionSess
        holder.binding.txtT2.setText(desSes);//
        holder.binding.txtT3.setText(desSes);
        holder.binding.txtT4.setText(desSes);

//        holder.binding.txtSes1.setText(list.get(1));
//        holder.binding.txtSes2.setText(list.get(4));
//        holder.binding.txtSes3.setText(list.get(7));
//        holder.binding.txtSes4.setText(list.get(10));
//
//        progres(holder.binding.progressBarUni,(Integer.parseInt(list.get(2)) + Integer.parseInt(list.get(5)) + Integer.parseInt(list.get(8)) + Integer.parseInt(list.get(11))) / 4);
//        progres(holder.binding.progressBarSes1,Integer.parseInt(list.get(2)));
//        progres(holder.binding.progressBarSes2,Integer.parseInt(list.get(5)));
//        progres(holder.binding.progressBarSes3,Integer.parseInt(list.get(8)));
//        progres(holder.binding.progressBarSes4,Integer.parseInt(list.get(11)));

        final Integer[] pos = {0};

//        holder.binding.txtT1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pos[0] =0+4*(unit.getIdUnit()-1);
//                goThem(list.get(0),list.get(1),Integer.parseInt(list.get(2)),_sessions.get(pos[0]).getIdUseSes().toString(),holder);
//            }
//        });
//        holder.binding.txtT2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pos[0] =1+4*(unit.getIdUnit()-1);
//                goThem(list.get(3),list.get(4),Integer.parseInt(list.get(5)),_sessions.get(pos[0]).getIdUseSes().toString(),holder);
//            }
//        });
//        holder.binding.txtT3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pos[0] =2+4*(unit.getIdUnit()-1);
//                goThem(list.get(6),list.get(7),Integer.parseInt(list.get(8)),_sessions.get(pos[0]).getIdUseSes().toString(),holder);
//            }
//        });
//        holder.binding.txtT4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pos[0] =3+4*(unit.getIdUnit()-1);
//                goThem(list.get(9),list.get(10),Integer.parseInt(list.get(11)),_sessions.get(pos[0]).getIdUseSes().toString(),holder);
//            }
//        });
//
//        holder.binding.btnItem2Eval.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String cod=unit.getIdUnit().toString();
//                String tit_unit=unit.getUniDesc();
//                Integer porc = holder.binding.progressBarUni.getProgress();
//                if(porc==100)
//                    goEval(v,cod,tit_unit);
//                else
//                    Toast.makeText(context, context.getString(R.string.error_msj4)+porc+"%", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void progres(ProgressBar progress, Integer porc) {
        progress.setProgress(1);
        ObjectAnimator animation = ObjectAnimator.ofInt(progress, "progress", progress.getProgress(), porc);
        animation.setDuration(6000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

    }

    private void goEval(View view,String cod,String tit_unit) {
        SharedPreferences log = context.getSharedPreferences(EVAL_SES,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putInt("idUnit",Integer.parseInt(cod));
        editor.putInt("idUser",Integer.parseInt(cod));
        editor.putString("sesTit",tit_unit);
        editor.putString("tipo","unidad");
        editor.putString("idSes","listarExerciceUnidad.php?Cod="+cod);
        editor.commit();
        Intent intent = new Intent(context, _Evaluacion.class);
//        Intent intent = new Intent(context, Evaluation.class);
        view.getContext().startActivity(intent);
    }
    private void goThem(String sesTit,String idSes,Integer sesP,String idUseSes,@NonNull @NotNull Adapter_Unit.UnitHolder view) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_4);

        TextView the = dialog.findViewById(R.id.txt_item4_theme);
        TextView eval = dialog.findViewById(R.id.txt_item4_eval);
        the.setText(sesTit);
        eval.setText(R.string.evalTit);

        the.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences log = context.getSharedPreferences(SESS_PREF,0);
                SharedPreferences.Editor editor = log.edit();
                editor.putString("sesTit",sesTit);
                editor.putInt("sesP",sesP);
                editor.putString("idUseSes",idUseSes);
                editor.putString("idSes", idSes);
                editor.commit();
                Intent intent = new Intent(context, DetailTheme.class);
                dialog.dismiss();
                view.itemView.getContext().startActivity(intent);
            }
        });

        eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences log = context.getSharedPreferences(EVAL_SES,0);
                SharedPreferences.Editor editor = log.edit();
                editor.putString("idUseSes",idUseSes);
                editor.putString("idSes","listarExercice.php?Cod="+idSes);
                editor.putString("tipo","sesion");
                editor.commit();
                Intent intent = new Intent(context, _Evaluacion.class);
                view.itemView.getContext().startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public int getItemCount() {
        return lista_Unit.size();
    }

    public static class UnitHolder extends RecyclerView.ViewHolder {
        ItemUnitBinding binding;
        public UnitHolder(@NonNull @NotNull ItemUnitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
    }}

//    private ArrayList<String> nueva_lista(ArrayList<QuoteModelUnidadSesion> lista_Ses, Integer id) {
//
//        ArrayList<QuoteModelUnidadSesion> sessions_Unit;
//        sessions_Unit = lista_Ses;
//        ArrayList<String> stringList = new ArrayList<String>();
//        stringList.clear();
//        for (int i=0;i<sessions_Unit.size(); i++){
//            if(sessions_Unit.get(i).getIdUnit()==id){
//                stringList.add(sessions_Unit.get(i).getSesDesc());
//                stringList.add(sessions_Unit.get(i).getIdSes().toString());
//                stringList.add(sessions_Unit.get(i).getSesPorc().toString());
//            }
//        }
//        return stringList;
//    }
}
