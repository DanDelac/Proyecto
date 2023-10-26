package com.example.proyecto.ui.view.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.proyecto.Adapter.Adapter_ImageSlider;
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

        presentationViewModel.getSlideModel().observe(this, new Observer<int[]>() {
            @Override
            public void onChanged(int[] ints) {
                Adapter_ImageSlider adapter = new Adapter_ImageSlider(Presentation.this, ints);
                binding.viewPager.setAdapter(adapter);
            }
        });
//        presentationViewModel.getSlideModel().observe(this, new Observer<List<SlideModel> >() {
//            @Override
//            public void onChanged(List<SlideModel> slideModelList) {
//                binding.sliderP.setImageList(slideModelList);
//            }
//        });
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if(aux<position)
                    aux=position;
                if(position==3||aux==3){
                    binding.presNext.setEnabled(true);
                    binding.presNext.setBackgroundResource(R.drawable.efredbuttons);
                }
            }
        });
        new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                binding.presSky.setText(String.valueOf(seconds));
                binding.presSky.setEnabled(false);
            }

            public void onFinish() {
                binding.presSky.setText(R.string.presSkip);
                binding.presSky.setEnabled(true); // Habilitar el botón
            }
        }.start();
//        binding.sliderP.setItemChangeListener(new ItemChangeListener() {
//            @Override
//            public void onItemChanged(int i) {
//                if(aux<i)
//                    aux=i;
//                if(i==3||aux==3){
//                    binding.presNext.setEnabled(true);
//                    binding.presNext.setBackgroundResource(R.drawable.efredbuttons);
//                }
//
////                Toast.makeText(Presentation.this, "Item: "+aux, Toast.LENGTH_SHORT).show();
//            }
//        });
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
        Intent i = new Intent(Presentation.this, Login.class);
        startActivity(i);
    }
}