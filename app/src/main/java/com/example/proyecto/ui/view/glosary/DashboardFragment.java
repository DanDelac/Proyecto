package com.example.proyecto.ui.view.glosary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyecto.Adapter.AdapterGlosary;
import com.example.proyecto.data.model.Glosary;
import com.example.proyecto.R;
import com.example.proyecto.domain.Util.Connection;
import com.example.proyecto.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    ArrayList<Glosary> glosaries;
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cargarGlosario();

        if(Connection.isConnectedToInternet(getContext()))
            Log.e("Conectado: ","True");
        else
            binding.txtGlosSC.setVisibility(View.VISIBLE);
//            Toast.makeText(getContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
        return root;
    }

    private void cargarGlosario() {
        glosaries = new ArrayList<>();
        int[] imageIds = new int[]{R.drawable.abecedario,R.drawable.pagweb,R.drawable.mas};
        glosaries.add(new Glosary(imageIds[0],"Evaluar alfabeto","Puedes practicar tomandote una foto y ver si es correcta la seña","0"));
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