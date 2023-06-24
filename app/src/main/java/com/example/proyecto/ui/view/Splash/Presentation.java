package com.example.proyecto.ui.view.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.proyecto.R;
import com.example.proyecto.databinding.ActivityPresentationBinding;
import com.example.proyecto.ui.view.RecoverPass.RecoverPass;
import com.example.proyecto.ui.view.Register.Register;
import com.example.proyecto.ui.view.login.Login;
import com.example.proyecto.ui.viewmodel.PresentationViewModel;

import java.util.List;

public class Presentation extends AppCompatActivity {

    private PresentationViewModel presentationViewModel;
    private ActivityPresentationBinding binding;
    public static final String LOG_PREF="log";
    private Integer aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aux=0;
        presentationViewModel =
                new ViewModelProvider(this).get(PresentationViewModel.class);

        binding = ActivityPresentationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presentationViewModel.onCreate();

        presentationViewModel.getSlideModel().observe(this, new Observer<List<SlideModel>>() {
            @Override
            public void onChanged(List<SlideModel> slideModelList) {
                binding.sliderP.setImageList(slideModelList);
            }
        });
        binding.sliderP.setItemChangeListener(new ItemChangeListener() {
            @Override
            public void onItemChanged(int i) {
                if(aux<i)
                    aux=i;
                if(i==3||aux==3){
                    binding.presNext.setEnabled(true);
                    binding.presNext.setBackgroundResource(R.drawable.efredbuttons);
                }

//                Toast.makeText(Presentation.this, "Item: "+aux, Toast.LENGTH_SHORT).show();
            }
        });
        binding.presSky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
//                Toast.makeText(Presentation.this, "Item: "+aux, Toast.LENGTH_SHORT).show();
            }
        });
        binding.presNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
//                Toast.makeText(Presentation.this, "Item: "+aux, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goLogin() {

        SharedPreferences log = getSharedPreferences(LOG_PREF,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putString("log","dislog");
        editor.commit();
        Intent i = new Intent(Presentation.this, Login.class);
        startActivity(i);
    }
}