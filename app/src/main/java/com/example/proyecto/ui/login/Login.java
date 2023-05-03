package com.example.proyecto.ui.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.ui.RecoverPass.RecoverPass;
import com.example.proyecto.ui.Register.Register;

public class Login extends AppCompatActivity {
    TextInputEditText edtUsuario, edtContra;
    Button btnIniSesion;
    TextView txtOlviContra, txtRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtlogUsuario);
        edtContra = findViewById(R.id.edtlogContrasena);

        txtOlviContra = findViewById(R.id.txtlogrecovPass);
        txtRegistrar = findViewById(R.id.txtlogRegister);

        btnIniSesion = findViewById(R.id.btnlogin);

        btnIniSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateLogin();
            }
        });
        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        txtOlviContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, RecoverPass.class);
            }
        });

    }

    private void ValidateLogin() {
    }
}