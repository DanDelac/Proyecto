package com.example.proyecto.ui.editaccount;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Entidades.VolleySingleton;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;

import org.json.JSONObject;

public class editAccount extends AppCompatActivity {

     TextInputEditText edtnwNombres, edtnwApellidos;
     Button btnGuardar, btnCancelar;

    public static final String LOG_PREF="log";
    String idUser,useName,useLastN;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        edtnwNombres=findViewById(R.id.edteditaccname);
        edtnwApellidos=findViewById(R.id.edteditacclastname);
        btnCancelar=findViewById(R.id.button_editacc_cancel);
        btnGuardar=findViewById(R.id.button_editacc_save);

        SharedPreferences preferences = getSharedPreferences(LOG_PREF,0);
        idUser = preferences.getString("idUser","nnn");
        useName = preferences.getString("useName","nnn");
        useLastN = preferences.getString("useLastN","nnn");

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        edtnwNombres.setHint(useName);
        edtnwApellidos.setHint(useLastN);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtnwNombres.getText().toString().replace(" ","").isEmpty()
                        ||edtnwApellidos.getText().toString().replace(" ","").isEmpty())
                    Toast.makeText(editAccount.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                else {
                        dialogAcept();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });
    }

    private void goHome() {
        SharedPreferences log = getSharedPreferences(LOG_PREF,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putString("log","log");
        editor.commit();
        Intent i = new Intent(editAccount.this, MainActivity.class);
        startActivity(i);
    }

    private void dialogAcept() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.question_ActUser))
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
        progreso = new ProgressDialog(editAccount.this);
        progreso.setMessage(getString(R.string.load_Register));
        progreso.show();
        String url = Util.RUTA+"actualizarUser.php" +
                "?Cod="+idUser +
                "&Nombres="+edtnwNombres.getText().toString()+
                "&Apellidos="+edtnwApellidos.getText().toString();
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
                String msj = response.optString("msj");
                if (msj.equals("[0]")) msj=getString(R.string.error_msj3);
                if (msj.equals("[1]")){
                    goHome();
                    msj=getString(R.string.msj1);

                    SharedPreferences log = getSharedPreferences(LOG_PREF,0);
                    SharedPreferences.Editor editor = log.edit();
                    editor.putString("useName",edtnwNombres.getText().toString());
                    editor.putString("useLastN",edtnwApellidos.getText().toString());
                    editor.commit();
                }

                Toast.makeText(editAccount.this, msj, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(editAccount.this, getString(R.string.error_General), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}