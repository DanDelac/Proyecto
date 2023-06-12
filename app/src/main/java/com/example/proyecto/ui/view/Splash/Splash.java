package com.example.proyecto.ui.view.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.proyecto.MainActivity;
import com.example.proyecto.databinding.ActivitySplashBinding;
import com.example.proyecto.ui.view.login.Login;

public class Splash extends AppCompatActivity {

    public static final String LOG_PREF="log";

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences preferences = getSharedPreferences(LOG_PREF,0);
        String log = preferences.getString("log","nnn");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(log.equals("nnn"))
                    startActivity(new Intent(Splash.this, Login.class));
                else
                    startActivity(new Intent(Splash.this, MainActivity.class));
//                startActivity(new Intent(Splash.this, DetailTheme.class));
                finish();
            }
        }, 3000);

    }
}