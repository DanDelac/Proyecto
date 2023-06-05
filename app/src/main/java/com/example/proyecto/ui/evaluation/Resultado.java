package com.example.proyecto.ui.evaluation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Entidades.VolleySingleton;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;
import com.example.proyecto.databinding.ActivityResultadoBinding;
import com.example.proyecto.ui.changepassword.changePassword;
import com.example.proyecto.ui.login.Login;

import org.json.JSONObject;

public class Resultado extends AppCompatActivity {
    private ActivityResultadoBinding binding;
    Float correct, incorrect,total;
    Integer idUnit;
    String idUseSes,tipo,idUser;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    private static final String EVAL_SES = "ID";
    public static final String LOG_PREF="log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences(EVAL_SES,0);
        correct = preferences.getFloat("correct",-1);
        incorrect = preferences.getFloat("incorrect",-1);
        total = preferences.getFloat("total",-1);
        idUseSes= preferences.getString("idUseSes","S/C");
        tipo= preferences.getString("tipo","S/C");
        idUnit= preferences.getInt("idUnit",0);

        SharedPreferences preferences1 = getSharedPreferences(LOG_PREF,0);
        idUser = preferences1.getString("idUser","nnn");

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
//        Toast.makeText(this, tipo+" - UserSes:" +idUseSes+"\nTotal: "+total+"Correctos:"+correct+" - Incorrectos:"+incorrect, Toast.LENGTH_SHORT).show();
        if((correct/total)>0.75){
            //aprobado
            if(tipo.equals("sesion")){
                actPorc();
            }else{
                actUnid();
            }
        }else{
            //reprobado
        }
        binding.btnBackE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        });
    }

    private void goLogin() {
        Intent i = new Intent(Resultado.this, MainActivity.class);
        startActivity(i);
    }

    private void actUnid() {
        Integer ses=idUnit*4;
        String url = Util.RUTA+"insertarUserSesion.php" +
                "?Cod=" + idUser+
                "&ses1=" +(ses+1)+
                "&ses2=" +(ses+2)+
                "&ses3=" +(ses+3)+
                "&ses4=" +(ses+4);
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(Resultado.this, "Unidades agregadas", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Resultado.this, getString(R.string.error_msj3), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());

            }
        });

        requestQueue.add(jsonObjectRequest);
    }
    private void actPorc() {
        String url = Util.RUTA+"actualizarUserSes.php?" +
                "Cod=" + idUseSes +
                "&Porc="+ 100;
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Toast.makeText(DetailTheme.this, "Porcentaje actualizado", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(DetailTheme.this, getString(R.string.error_msj3), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Resultado.this, MainActivity.class));
        finish();
    }
}
