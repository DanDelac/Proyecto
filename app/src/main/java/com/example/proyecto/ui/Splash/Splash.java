package com.example.proyecto.ui.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.ui.login.Login;

public class Splash extends AppCompatActivity {

    public static final String LOG_PREF="log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences = getSharedPreferences(LOG_PREF,0);
        String log = preferences.getString("log","nnn");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(log.equals("nnn"))
                startActivity(new Intent(Splash.this, Login.class));
                else
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, 3000);

    }
}