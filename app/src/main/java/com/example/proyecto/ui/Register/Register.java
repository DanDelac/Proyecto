package com.example.proyecto.ui.Register;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Entidades.VolleySingleton;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;
import com.example.proyecto.databinding.ActivityRegisterBinding;
import com.example.proyecto.ui.login.Login;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;


public class Register extends AppCompatActivity  {
    private ActivityRegisterBinding binding;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    String aux=null,aux1=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        binding.btnReRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edtReLastName.getText().toString().replace(" ","").isEmpty()
                        || binding.edtRePassword.getText().toString().replace(" ","").isEmpty()
                        || binding.edtRePassword2.getText().toString().replace(" ","").isEmpty()
                        || binding.edtReEmail.getText().toString().replace(" ","").isEmpty()
                        || binding.edtReAccount.getText().toString().replace(" ","").isEmpty()
                        || binding.edtReNames.getText().toString().replace(" ","").isEmpty())
                    Toast.makeText(Register.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                else {
                    aux = binding.edtRePassword.getText().toString();
                    aux1 = binding.edtRePassword2.getText().toString();
                    if (aux.equals(aux1)) {
                        dialogAcept();
                    } else
                        Toast.makeText(Register.this, getString(R.string.error_Pass), Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.txtReCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        });
    }

    private void dialogAcept() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.question_Register))
                .setPositiveButton(getString(R.string.question_Yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        regUser();
                    }})
                .setNegativeButton(getString(R.string.question_No), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }

    private void regUser() {
        progreso = new ProgressDialog(Register.this);
        progreso.setMessage(getString(R.string.load_Register));
        progreso.show();
        String url = Util.RUTA+"insertarUser.php" +
                "?Nombres="+binding.edtReNames.getText().toString()+
                "&Apellidos="+binding.edtReLastName.getText().toString() +
                "&Cuenta="+ binding.edtReAccount.getText().toString() +
                "&Pass="+ binding.edtRePassword.getText().toString() +
                "&Correo="+ binding.edtReEmail.getText().toString();
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
                String msj = response.optString("msj");
                if (msj.equals("[-1]")) msj=getString(R.string.error_msj1);
                if (msj.equals("[0]")) msj=getString(R.string.error_msj2);
                if (msj.equals("[1]")){
                    goLogin();
                    msj=getString(R.string.msj);
                }

                Toast.makeText(Register.this, msj, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, getString(R.string.error_General), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void goLogin() {
        Intent i = new Intent(Register.this,Login.class);
        startActivity(i);
    }
}