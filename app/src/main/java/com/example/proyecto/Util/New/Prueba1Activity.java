package com.example.proyecto.Util.New;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proyecto.R;

public class Prueba1Activity extends AppCompatActivity {

    TextView txt_1A;
    Pruab pruab = new Pruab();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_prueba);

        txt_1A = findViewById(R.id.txt_1A);
        txt_1A.setText(pruab.Get_Prueba());
    }

}