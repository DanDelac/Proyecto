package com.example.proyecto.ui.glosary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.proyecto.Adapter.AdapterGlosary;
import com.example.proyecto.Entidades.Glosary;
import com.example.proyecto.R;
import com.example.proyecto.Util.Connection;
import com.example.proyecto.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    ArrayList<Glosary> glosaries;
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(Connection.isConnectedToInternet(getContext()))
            cargarGlosario();
        else
            binding.txtGlosSC.setVisibility(View.VISIBLE);
//            Toast.makeText(getContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
        return root;
    }

    private void cargarGlosario() {
        glosaries = new ArrayList<>();
        int[] imageIds = new int[]{R.drawable.abecedario,R.drawable.pagweb,R.drawable.mas};
        glosaries.add(new Glosary(imageIds[0],"Abecedario","Ver las señas del abecedario","0"));
        glosaries.add(new Glosary(imageIds[1],"CONADIS","Ingresar a la página de CONADIS","https://www.gob.pe/institucion/conadis/institucional"));
        glosaries.add(new Glosary(imageIds[1],"GUIA PARA EL APRENDIZAJE DE LA LSP","Ingresar al repositorio del ministerio","https://repositorio.minedu.gob.pe/handle/20.500.12799/5545"));
        glosaries.add(new Glosary(imageIds[2],"CONADIS","Ver vídeo de conadis","GfvVw4eNw7w"));
        glosaries.add(new Glosary(imageIds[2],"Abecedario","Ver vídeo de abecedario","aJ8SV3g9OTE"));
        glosaries.add(new Glosary(imageIds[2],"Saludos","Ver vídeo de saludos","4Pmnh4tRwuk"));
        glosaries.add(new Glosary(imageIds[2],"Manos que conectan","Ver lista completa de la PUCP","wl4f6FaHOEw&list=PLWDrEPTsXAxMvS1sL9Ebfpa2LsYz3OZD_"));
        AdapterGlosary adapter = new AdapterGlosary(getContext(),glosaries);
        binding.glosRecycler.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}