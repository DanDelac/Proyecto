package com.example.proyecto.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Entidades.Account;
import com.example.proyecto.Entidades.VolleySingleton;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;
import com.example.proyecto.ui.RecoverPass.RecoverPass;
import com.example.proyecto.ui.Register.Register;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class  Login extends AppCompatActivity {
    TextInputEditText edtUsuario, edtContra;
    Button btnIniSesion;
    TextView txtOlviContra, txtRegistrar;

    public static final String LOG_PREF="log";

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ArrayList<Account> lstAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtlogUsuario);
        edtContra = findViewById(R.id.edtlogContrasena);

        txtOlviContra = findViewById(R.id.txtlogrecovPass);
        txtRegistrar = findViewById(R.id.txtlogRegister);

        btnIniSesion = findViewById(R.id.btnlogin);

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        lstAcc=new ArrayList<>();

        btnIniSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUsuario.getText().toString().replace(" ","").isEmpty()||edtContra.getText().toString().replace(" ","").isEmpty())
                    Toast.makeText(Login.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                else
                    access();
            }
        });

        edtContra.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean action = false;
                if (i == EditorInfo.IME_ACTION_SEARCH)
                {
                    if(edtUsuario.getText().toString().replace(" ","").isEmpty()||edtContra.getText().toString().replace(" ","").isEmpty())
                        Toast.makeText(Login.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                    else
                        access();
                }
                return action;
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

    private void access() {
        progreso = new ProgressDialog(this);
        progreso.setMessage(getString(R.string.load_Login));
        progreso.show();
        String url = Util.RUTA+"/accesoUser.php" +
                "?Cuenta=" +edtUsuario.getText().toString()+
                "&Pass=" +edtContra.getText().toString();
        url=url.replace(" ","%20");
        Log.e("URL LOGIN: ",url);

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Account account = null;
                JSONArray jsonArray = response.optJSONArray("tblUser");
                Integer aux=0;
                try{
                    for(int i=0;i<jsonArray.length();i++){
                        account = new Account();
                        JSONObject jsonObject = null;
                        jsonObject = jsonArray.getJSONObject(i);
                        aux=jsonObject.getInt("idUser");
                        account.setIdUser(jsonObject.getString("idUser"));
                        account.setUseName(jsonObject.getString("useName"));
                        account.setUseLastN(jsonObject.getString("useLastN"));
                        account.setUseCorre(jsonObject.getString("useCorre"));
                        lstAcc.add(account);


                        SharedPreferences log = getSharedPreferences(LOG_PREF,0);
                        SharedPreferences.Editor editor = log.edit();
                        editor.putString("idUser",account.getIdUser());
                        editor.putString("useName",account.getUseName());
                        editor.putString("useLastN",account.getUseLastN());
                        editor.putString("useCorre",account.getUseCorre());
                        editor.commit();
                    }

                    if(aux!=-1) goHome();
                    else{progreso.hide(); Toast.makeText(Login.this, getString(R.string.error_Login), Toast.LENGTH_SHORT).show();
                    };
                }catch (Exception e){
                    Log.e("ERROR LOGIN: ",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void goHome() {

        SharedPreferences log = getSharedPreferences(LOG_PREF,0);
        SharedPreferences.Editor editor = log.edit();
        editor.putString("log","log");
        editor.commit();
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }
}