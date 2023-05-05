package com.example.proyecto.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto.Adapter.AdapterSession;
import com.example.proyecto.Entidades._Session;
import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;
import com.example.proyecto.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recycler_unit;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    public static final String LOG_PREF="log";

    ArrayList<Unit> units;
    ArrayList<_Session> _sessions;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        configuracion(root);
        return root;
    }

    private void configuracion(View root) {
        recycler_unit=root.findViewById(R.id.recycler_unit);
        recycler_unit.setLayoutManager(new LinearLayoutManager(getContext()));

        requestQueue= Volley.newRequestQueue(getContext());
        CargarUnidades();
    }

    private void CargarUnidades() {
        String idUser = null;
        units = new ArrayList<>();
        _sessions = new ArrayList<>();

        SharedPreferences preferences = getActivity().getSharedPreferences(LOG_PREF,0);
        idUser = preferences.getString("idUser","nnn");

        String url = Util.RUTA+"listarSesion.php?Cod="+idUser;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONArray jsonArray = response.optJSONArray("tblLibros");
                    Unit unit = null;
                    _Session _session = null;
                    try{
                        for (int i=0;i<jsonArray.length(); i=i+4){
                            unit = new Unit();
                            JSONObject jsonObject=null;
                            jsonObject = jsonArray.getJSONObject(i);

                            unit.setUniDesc(jsonObject.getString("uniDesc"));
                            units.add(unit);
                        }

                        for (int i=0;i<jsonArray.length(); i++){
                            _session = new _Session();
                            JSONObject jsonObject=null;
                            jsonObject = jsonArray.getJSONObject(i);

                            _session.setSesDesc(jsonObject.getString("sesDesc"));
                            _sessions.add(_session);
                        }
                        AdapterSession adapterSession = new AdapterSession(units,_sessions);
                        recycler_unit.setAdapter(adapterSession);
                    }
                    catch (Exception e){

                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}