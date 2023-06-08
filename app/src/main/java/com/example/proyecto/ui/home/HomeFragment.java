package com.example.proyecto.ui.home;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto.Adapter.Adapter_Unit;
import com.example.proyecto.Entidades._Session;
import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.Util.Connection;
import com.example.proyecto.Util.New.Kotlinclass.ModelUnit;
import com.example.proyecto.Util.New.Kotlinclass.QuoteModelUnidadSesion;
import com.example.proyecto.Util.New.Kotlinclass.QuoteViewModel;
import com.example.proyecto.Util.Util;
import com.example.proyecto.databinding.FragmentHomeBinding;
import com.example.proyecto.ui.glosary.DashboardViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private QuoteViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    public static final String LOG_PREF="log";

    ArrayList<Unit> units;
    ArrayList<_Session> _sessions;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(QuoteViewModel.class);
//        new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(QuoteViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        SharedPreferences preferences = getActivity().getSharedPreferences(LOG_PREF,0);
//        String idUser = preferences.getString("idUser","nnn");
        homeViewModel.onCreate("1");

        requestQueue= Volley.newRequestQueue(getContext());
        if(Connection.isConnectedToInternet(getContext()))
            CargarUnidades2();
        else
            binding.txtHomeSC.setVisibility(View.VISIBLE);
//            Toast.makeText(getContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
        return root;
    }
    private void CargarUnidades2(){

        ArrayList<ModelUnit> list_Unit = new ArrayList<>();
        ArrayList<QuoteModelUnidadSesion> list_UserSes = new ArrayList<>();
        final ModelUnit[] unit = {null};
        final QuoteModelUnidadSesion[] oUnitSes = {null};
        homeViewModel.getQuoteModel().observe(this, new Observer<List<QuoteModelUnidadSesion>>() {
            @Override
            public void onChanged(List<QuoteModelUnidadSesion> list_unidades) {

                for (int i=0;i<list_unidades.size(); i=i+4){
                    unit[0] = new ModelUnit(list_unidades.get(i).getOSes().getOUnit().getIdUnit()
                            ,list_unidades.get(i).getOSes().getOUnit().getUniDesc());
                    list_Unit.add(unit[0]);
                }
                for (int i=0;i<list_unidades.size(); i++){
                    oUnitSes[0] = new QuoteModelUnidadSesion(list_unidades.get(i).getIdUseSes(),
                            list_unidades.get(i).getIdUser(),
                            list_unidades.get(i).getOSes(),list_unidades.get(i).getSesPorc());
                    list_UserSes.add(oUnitSes[0]);
                }
                Adapter_Unit adapterUnit = new Adapter_Unit(getContext(), list_Unit, list_UserSes);
                binding.recyclerUnit.setAdapter(adapterUnit);
            }
        });
    }

//    private void CargarUnidades() {
//        String idUser = null;
//        units = new ArrayList<>();
//        _sessions = new ArrayList<>();
//        units.clear();
//        _sessions.clear();
//
//        SharedPreferences preferences = getActivity().getSharedPreferences(LOG_PREF,0);
//        idUser = preferences.getString("idUser","nnn");
//
//        String url = Util.RUTA+"listarSesion.php?Cod="+idUser;
//        url=url.replace(" ","%20");
//        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
//            new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    JSONArray jsonArray = response.optJSONArray("tblLibros");
//                    Unit unit = null;
//                    _Session _session = null;
//                    try{
//                        for (int i=0;i<jsonArray.length(); i=i+4){
//                            unit = new Unit();
//                            JSONObject jsonObject=null;
//                            jsonObject = jsonArray.getJSONObject(i);
//
//                            unit.setUniDesc(jsonObject.getString("uniDesc"));
//                            unit.setIdUnit(jsonObject.getInt("idUnit"));
//                            unit.setIdUseUni(jsonObject.getInt("idUseSes"));
//                            units.add(unit);
//                        }
//
//                        for (int i=0;i<jsonArray.length(); i++){
//                            _session = new _Session();
//                            JSONObject jsonObject=null;
//                            jsonObject = jsonArray.getJSONObject(i);
//
//                            _session.setIdSes(jsonObject.getInt("idSes"));
//                            _session.setIdUseSes(jsonObject.getInt("idUseSes"));
//                            _session.setIdUnit(jsonObject.getInt("idUnit"));
//                            _session.setSesDesc(jsonObject.getString("sesDesc"));
//                            _session.setSesPorc(jsonObject.getInt("sesPorc"));
//                            _sessions.add(_session);
//                        }
//                        Adapter_Unit adapterUnit = new Adapter_Unit(getContext(),units,_sessions);
//                        binding.recyclerUnit.setAdapter(adapterUnit);
//                    }
//                    catch (Exception e){
//
//                    }
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//        requestQueue.add(jsonObjectRequest);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}