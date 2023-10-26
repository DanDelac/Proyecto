package com.example.proyecto.domain.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Adapter.AdapterTheme;
import com.example.proyecto.R;
import com.example.proyecto.data.model.Theme;
import com.example.proyecto.data.model.VolleySingleton;
import com.example.proyecto.ui.view.Theme.DetailTheme;
import com.example.proyecto.ui.view.Theme.Detail_Carousel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Datos_Detalles {

    public static final String SESS_PREF="session";
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    String idSes, idUseSes; Integer sesP;

    ArrayList<Theme> lstTheme;
    public void Datos_(Context context){

        //#region LLAMADA AL SERVIDOR
        SharedPreferences preferences = context.getSharedPreferences(SESS_PREF,0);
        idSes = preferences.getString("idSes","nnn");
        idUseSes = preferences.getString("idUseSes","nnn");
        sesP = preferences.getInt("sesP",-1);
        String sesTit = preferences.getString("sesTit","nnn");
        requestQueue = VolleySingleton.getmInstance(context).getRequestQueue();
//        cargarTheme();
        String url = Util.RUTA+"listarTheme.php?Cod="+idSes ;
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Theme theme = null;
                JSONArray json = response.optJSONArray("tblTheme");
                try {
                    for (int i = 0; i < json.length(); i++) {
                        theme = new Theme();
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);
                        theme.setIdTheme(jsonObject.getString("idTheme"));
                        theme.setIdSes(jsonObject.getString("idSes"));
                        theme.setImgTit(jsonObject.getString("imgTit"));
                        theme.setImgDesc(jsonObject.getString("imgDesc"));
                        theme.setSesDesc(jsonObject.getString("sesDesc"));
                        theme.setImg(jsonObject.getString("img"));
                        lstTheme.add(theme);
                    }
                } catch (Exception e) {
//            Toast.makeText(getContext(), "no conecta a la DB", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, context.getString(R.string.error_General), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
        //#endregion

        if (1==0){
            Intent toThemeGalery = new Intent(context, DetailTheme.class);
            context.startActivity(toThemeGalery);
        }else{
            Intent toThemeCarousel = new Intent(context, Detail_Carousel.class);
            context.startActivity(toThemeCarousel);
        }
    }

//    private void cargarTheme() {
//    }
}
