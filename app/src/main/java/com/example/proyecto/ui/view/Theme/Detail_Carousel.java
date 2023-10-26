package com.example.proyecto.ui.view.Theme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Adapter.AdapterTheme;
import com.example.proyecto.Adapter.Adapter_ThemeSlider;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.data.model.Theme;
import com.example.proyecto.data.model.VolleySingleton;
import com.example.proyecto.databinding.ActivityDetailCarouselBinding;
import com.example.proyecto.databinding.ActivityThemeBinding;
import com.example.proyecto.domain.Util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Detail_Carousel extends AppCompatActivity {

    ActivityDetailCarouselBinding binding;
    ArrayList<Theme> lstTheme;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    public static final String SESS_PREF="session";
    String idSes, idUseSes; Integer sesP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailCarouselBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences(SESS_PREF,0);
        idSes = preferences.getString("idSes","nnn");
        idUseSes = preferences.getString("idUseSes","nnn");
        sesP = preferences.getInt("sesP",-1);
        String sesTit = preferences.getString("sesTit","nnn");
        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        binding.txtTheme.setText(sesTit);
//        Toast.makeText(this, "idUseSes: "+ idUseSes +"\nPorc: "+sesP, Toast.LENGTH_SHORT).show();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detail_Carousel.this, MainActivity.class);
                startActivity(i);
            }
        });
        lstTheme= new ArrayList<>();
        cargarTheme();
    }

    private void cargarTheme() {
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
                    Adapter_ThemeSlider adapter_themeSlider = new Adapter_ThemeSlider(Detail_Carousel.this, lstTheme);
                    binding.viewPager2Detalles.setAdapter(adapter_themeSlider);


                } catch (Exception e) {
//            Toast.makeText(getContext(), "no conecta a la DB", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Detail_Carousel.this, getString(R.string.error_General), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}