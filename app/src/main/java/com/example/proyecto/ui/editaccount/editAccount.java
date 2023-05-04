package com.example.proyecto.ui.editaccount;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.proyecto.R;

public class editAccount extends AppCompatActivity {

     TextInputEditText edtnwNombres, edtnwApellidos;
     Button btnGuardar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
    }
}