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
import android.widget.Toast;

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
    Integer posicion;
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
        this.posicion=position;
//        ArrayList<String> list = nueva_lista(List_UserSes,unit.getIdUnit());
        holder.binding.txtUnit2.setText(unit.getUniDesc());
        Integer sesPos=4*position;
        //Descripción
        cargarDesc(holder.binding.txtT1,sesPos+0);
        cargarDesc(holder.binding.txtT2,sesPos+1);
        cargarDesc(holder.binding.txtT3,sesPos+2);
        cargarDesc(holder.binding.txtT4,sesPos+3);
        //ID sesión
        cargarSes(holder.binding.txtSes1,sesPos+0);
        cargarSes(holder.binding.txtSes2,sesPos+1);
        cargarSes(holder.binding.txtSes3,sesPos+2);
        cargarSes(holder.binding.txtSes4,sesPos+3);
        //Progreso ses
        Integer aux=0;
        for (int i =0; i<4;i++){
            aux+=List_UserSes.get(sesPos+i).getSesPorc();
        }
        cargarProg(holder.binding.progressBarSes1,List_UserSes.get(sesPos+0).getSesPorc());
        cargarProg(holder.binding.progressBarSes2,List_UserSes.get(sesPos+1).getSesPorc());
        cargarProg(holder.binding.progressBarSes3,List_UserSes.get(sesPos+2).getSesPorc());
        cargarProg(holder.binding.progressBarSes4,List_UserSes.get(sesPos+3).getSesPorc());
        cargarProg(holder.binding.progressBarUni,aux/4);

        holder.binding.llS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goThem(List_UserSes.get(sesPos).getOSes().getSesDesc(),
                        String.valueOf(List_UserSes.get(sesPos).getOSes().getIdSes()),
                        List_UserSes.get(sesPos).getSesPorc(),
                        String.valueOf(List_UserSes.get(sesPos).getIdUseSes()));
            }
        });
        holder.binding.llS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goThem(List_UserSes.get(sesPos+1).getOSes().getSesDesc(),
                        String.valueOf(List_UserSes.get(sesPos+1).getOSes().getIdSes()),
                        List_UserSes.get(sesPos+1).getSesPorc(),
                        String.valueOf(List_UserSes.get(sesPos+1).getIdUseSes()));
            }
        });
        holder.binding.llS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goThem(List_UserSes.get(sesPos+2).getOSes().getSesDesc(),
                        String.valueOf(List_UserSes.get(sesPos+2).getOSes().getIdSes()),
                        List_UserSes.get(sesPos+2).getSesPorc(),
                        String.valueOf(List_UserSes.get(sesPos+2).getIdUseSes()));
            }
        });
        holder.binding.llS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goThem(List_UserSes.get(sesPos+3).getOSes().getSesDesc(),
                        String.valueOf(List_UserSes.get(sesPos+3).getOSes().getIdSes()),
                        List_UserSes.get(sesPos+3).getSesPorc(),
                        String.valueOf(List_UserSes.get(sesPos+3).getIdUseSes()));
            }
        });
        holder.binding.btnItem2Eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod=String.valueOf(unit.getIdUnit());
                String tit_unit=unit.getUniDesc();
                Integer porc = holder.binding.progressBarUni.getProgress();
                if(porc==100)
                    goEval(cod,tit_unit);
                else
                    Toast.makeText(context, context.getString(R.string.error_msj4)+porc+"%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarSes(TextView txt, Integer pos) {
        String desSes = String.valueOf(List_UserSes.get(pos).getOSes().getIdSes());
        txt.setText(desSes);
    }
    private void cargarDesc(TextView txt, Integer pos) {
        String desSes = List_UserSes.get(pos).getOSes().getSesDesc();
        txt.setText(desSes);
    }

    private void cargarProg(ProgressBar progress, Integer porc) {
        progress.setProgress(1);
        ObjectAnimator animation = ObjectAnimator.ofInt(progress, "progress", progress.getProgress(), porc);
        animation.setDuration(6000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

    }

    private void goEval(String cod,String tit_unit) {
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
        context.startActivity(intent);
    }
    private void goThem(String sesTit,String idSes,Integer sesP,String idUseSes) {
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
                context.startActivity(intent);
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
                context.startActivity(intent);
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
}
