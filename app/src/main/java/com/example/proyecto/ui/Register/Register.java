package com.example.proyecto.ui.Register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.ui.login.Login;

public class Register extends AppCompatActivity {
    TextInputEditText edtNombre, edtApellidos, edtCorreo, edtCuenta, edtContra;
    Button btnRegistrar;
    TextView txtCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNombre = findViewById(R.id.edtReNames);
        edtApellidos = findViewById(R.id.edtReLastName);
        edtCorreo = findViewById(R.id.edtReEmail);
        edtCuenta = findViewById(R.id.edtReAccount);
        edtContra = findViewById(R.id.edtRePassword);

        txtCancelar = findViewById(R.id.txtReCancelar);
        btnRegistrar = findViewById(R.id.btnReRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAcept();
            }
        });
        txtCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irLogin();
            }
        });
    }

    private void irLogin() {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }

    private void dialogAcept() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea registrar esta cuenta?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //registrarnuevo();
                    }})
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }
}