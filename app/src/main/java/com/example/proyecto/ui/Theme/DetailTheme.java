package com.example.proyecto.ui.Theme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Adapter.AdapterTheme;
import com.example.proyecto.Entidades.Theme;
import com.example.proyecto.Entidades.VolleySingleton;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;
import com.synnapps.carouselview.CarouselView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailTheme extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Theme> lstTheme;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    Button btnBack;
    TextView txtTit;

    public static final String SESS_PREF="session";
    String idSes,idUseUni,sesD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        recyclerView = findViewById(R.id.rv_theme);

        txtTit = findViewById(R.id.txt_Theme);
        btnBack = findViewById(R.id.btnBack);

        SharedPreferences preferences = getSharedPreferences(SESS_PREF,0);
        idSes = preferences.getString("idSes","nnn");
        idUseUni = preferences.getString("idUseUni","nnn");
        sesD = preferences.getString("sesD","nnn");
        String sesTit = preferences.getString("sesTit","nnn");
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        txtTit.setText(sesTit);
        Toast.makeText(this, "idUseUni: "+idUseUni+"\nsesD: "+sesD, Toast.LENGTH_SHORT).show();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailTheme.this, MainActivity.class);
                startActivity(i);
            }
        });


        lstTheme= new ArrayList<>();
        cargarTheme();
    }

    private void cargarTheme() {
        lstTheme.clear();
//        listView.setAdapter(new ArrayAdapter<Theme>(DetailTheme.this, android.R.layout.simple_list_item_1,lstTheme));

        recyclerView.setLayoutManager(new GridLayoutManager(DetailTheme.this, 2));
        recyclerView.setHasFixedSize(true);

        progreso = new ProgressDialog(DetailTheme.this);
        progreso.setMessage(getString(R.string.load_Theme));
        progreso.show();
        String url = Util.RUTA+"listarTheme.php?Cod="+idSes ;
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
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
                    AdapterTheme adapterTheme = new AdapterTheme(DetailTheme.this, lstTheme, 14);
                    recyclerView.setAdapter(adapterTheme);


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