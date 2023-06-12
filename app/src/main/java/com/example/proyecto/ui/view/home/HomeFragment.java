package com.example.proyecto.ui.view.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyecto.Adapter.Adapter_Unit;
import com.example.proyecto.domain.Util.Connection;
import com.example.proyecto.data.model.ModelUnit;
import com.example.proyecto.data.model.QuoteModelUnidadSesion;
import com.example.proyecto.ui.viewmodel.HomeViewModel;
import com.example.proyecto.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public static final String LOG_PREF="log";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
//        new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(QuoteViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences preferences = getActivity().getSharedPreferences(LOG_PREF,0);
        String idUser = preferences.getString("idUser","nnn");
        homeViewModel.onCreate(idUser);

        if(Connection.isConnectedToInternet(getContext()))
            CargarUnidades2();
        else
            binding.txtHomeSC.setVisibility(View.VISIBLE);
//            Toast.makeText(getContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
        return root;
    }
    private void CargarUnidades2(){

        homeViewModel.getQuoteModel().observe(this, new Observer<List<QuoteModelUnidadSesion>>() {
            @Override
            public void onChanged(List<QuoteModelUnidadSesion> list_unidades) {

                ArrayList<ModelUnit> list_Unit = new ArrayList<>();
                ArrayList<QuoteModelUnidadSesion> list_UserSes = new ArrayList<>();
                final ModelUnit[] unit = {null};
                final QuoteModelUnidadSesion[] oUnitSes = {null};

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}