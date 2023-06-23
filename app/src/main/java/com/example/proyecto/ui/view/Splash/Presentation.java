package com.example.proyecto.ui.view.Splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.proyecto.databinding.ActivityPresentationBinding;
import com.example.proyecto.ui.viewmodel.PresentationViewModel;

import java.util.List;

public class Presentation extends AppCompatActivity {

    private PresentationViewModel presentationViewModel;
    private ActivityPresentationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        binding.sliderP.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(Presentation.this, i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doubleClick(int i) {
                Toast.makeText(Presentation.this, "dd", Toast.LENGTH_SHORT).show();
            }
        });
    }
}