package com.example.proyecto.ui.Theme;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Adapter.AdapterTheme;
import com.example.proyecto.Entidades.Theme;
import com.example.proyecto.Entidades.VolleySingleton;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailTheme extends AppCompatActivity {


    ListView listView;
    ArrayList<Theme> lstTheme;


    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    public static final String SESS_PREF="session";
    String idSes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);


        SharedPreferences preferences = getSharedPreferences(SESS_PREF,0);
        idSes = preferences.getString("idSes","1");

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        lstTheme= new ArrayList<>();
//        cargarTheme();

    }

    private void cargarTheme() {
        lstTheme.clear();
        listView.setAdapter(new ArrayAdapter<Theme>(DetailTheme.this, android.R.layout.simple_list_item_1,lstTheme));
        progreso = new ProgressDialog(DetailTheme.this);
        progreso.setMessage(getString(R.string.load_Register));
        progreso.show();
        String url = Util.RUTA+"listarTheme.php?Cod="+idSes ;
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
                Theme theme = null;
                JSONArray json = response.optJSONArray("tblLibros");
                try {
                    for (int i = 0; i < json.length(); i++) {
                        theme = new Theme();
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);
                        theme.setIdSes(jsonObject.getString("idSes"));
                        theme.setSesDesc(jsonObject.getString("sesDesc"));
                        theme.setIdTheme(jsonObject.getString("idTheme"));
                        theme.setTheDesc(jsonObject.getString("theDesc"));
//                        theme.setDataImagen(jsonObject.getString("theImg"));
                        lstTheme.add(theme);
                    }
                    AdapterTheme adapterTheme = new AdapterTheme(DetailTheme.this, lstTheme, 14);
                    listView.setAdapter(adapterTheme);
                } catch (Exception e) {
//            Toast.makeText(getContext(), "no conecta a la DB", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailTheme.this, getString(R.string.error_General), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}