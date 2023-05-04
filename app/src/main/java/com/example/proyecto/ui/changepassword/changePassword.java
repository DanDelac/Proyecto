package com.example.proyecto.ui.changepassword;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.proyecto.ui.login.Login;

import org.json.JSONObject;

public class changePassword extends AppCompatActivity {
    TextView txvUser,txvAccount,txvMail;
    EditText edtPass1, edtPass2;
    Button btnCancel, btnAceptar;

    public static final String LOG_PREF="log";
    String idUser,useAccount,useName,useLastN,useCorre;
    String aux,aux1;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txvUser=findViewById(R.id.txt_changepass_user);
        txvAccount=findViewById(R.id.txt_changepass_account);
        txvMail=findViewById(R.id.txt_changepass_mail);
        edtPass1=findViewById(R.id.edtchangepass1);
        edtPass2=findViewById(R.id.edtchangepass2);
        btnAceptar=findViewById(R.id.button_changepass_save);
        btnCancel=findViewById(R.id.button_changepass_cancel);

        SharedPreferences preferences = getSharedPreferences(LOG_PREF,0);
        idUser = preferences.getString("idUser","nnn");
        useAccount = preferences.getString("useAccount","nnn");
        useName = preferences.getString("useName","nnn");
        useLastN = preferences.getString("useLastN","nnn");
        useCorre = preferences.getString("useCorre","nnn");

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        txvUser.setText(useLastN+", "+useName);
        txvAccount.setText(useAccount);
        txvMail.setText(useCorre);


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtPass1.getText().toString().replace(" ","").isEmpty()
                        ||edtPass2.getText().toString().replace(" ","").isEmpty())
                    Toast.makeText(changePassword.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                else {
                    aux = edtPass1.getText().toString();
                    aux1 = edtPass2.getText().toString();
                    if (aux.equals(aux1)) {
                        dialogAcept();
                    } else
                        Toast.makeText(changePassword.this, getString(R.string.error_Pass), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(changePassword.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    private void goLogin() {

        SharedPreferences log = getSharedPreferences(LOG_PREF,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putString("log","nnn");
        editor.commit();
        Intent i = new Intent(changePassword.this, Login.class);
        startActivity(i);
    }

    private void dialogAcept() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.question_ActPass))
                .setPositiveButton(getString(R.string.question_Yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actPass();
                    }})
                .setNegativeButton(getString(R.string.question_No), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.show();
    }


    private void actPass() {
        progreso = new ProgressDialog(changePassword.this);
        progreso.setMessage(getString(R.string.load_Register));
        progreso.show();
        String url = Util.RUTA+"actualizarPass.php" +
                "?Cod="+idUser +
                "&Pass=" +aux;
        url=url.replace(" ","%20");
        Log.d("Url : ",url.toString());

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
                String msj = response.optString("msj");
                if (msj.equals("[0]")) msj=getString(R.string.error_msj3);
                if (msj.equals("[1]")){
                    goLogin();
                    msj=getString(R.string.msj1);
                }

                Toast.makeText(changePassword.this, msj, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(changePassword.this, getString(R.string.error_General), Toast.LENGTH_SHORT).show();
                Log.e(" ERROR: ", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}