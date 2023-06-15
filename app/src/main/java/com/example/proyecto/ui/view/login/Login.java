package com.example.proyecto.ui.view.login;

import static com.example.proyecto.domain.Util.Segurity.encodePassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.data.model.Account;
import com.example.proyecto.data.model.VolleySingleton;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.domain.Util.Util;
import com.example.proyecto.databinding.ActivityLoginBinding;
import com.example.proyecto.ui.view.RecoverPass.RecoverPass;
import com.example.proyecto.ui.view.Register.Register;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class  Login extends AppCompatActivity {
    private ActivityLoginBinding binding;

    public static final String LOG_PREF="log";

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    ArrayList<Account> lstAcc;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        lstAcc=new ArrayList<>();

        binding.checkBoxShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    binding.edtlogContrasena.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    binding.edtlogContrasena.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edtlogUsuario.getText().toString().replace(" ","").isEmpty()||
                   binding.edtlogContrasena.getText().toString().replace(" ","").isEmpty())
                    Toast.makeText(Login.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                else
                    access();
            }
        });

        binding.edtlogContrasena.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean action = false;
                if (i == EditorInfo.IME_ACTION_SEARCH)
                {
                    if(binding.edtlogUsuario.getText().toString().replace(" ","").isEmpty()||
                       binding.edtlogContrasena.getText().toString().replace(" ","").isEmpty())
                        Toast.makeText(Login.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                    else
                        access();
                }
                return action;
            }
        });

        binding.txtlogRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        binding.txtlogrecovPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, RecoverPass.class);
                startActivity(i);
            }
        });


    }

    private void access() {
        progreso = new ProgressDialog(this);
        progreso.setMessage(getString(R.string.load_Login));
        progreso.show();
//        String pass = encodePassword(binding.edtlogContrasena.getText().toString());
        String pass = (binding.edtlogContrasena.getText().toString());
        String url = Util.RUTA+"/accesoUser.php" +
                "?Cuenta=" +binding.edtlogUsuario.getText().toString()+
                "&Pass=" +pass;
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
                        account.setUseAccount(binding.edtlogUsuario.getText().toString());
                        lstAcc.add(account);


                        SharedPreferences log = getSharedPreferences(LOG_PREF,0);
                        SharedPreferences.Editor editor = log.edit();
                        editor.putString("idUser",account.getIdUser());
                        editor.putString("useAccount",account.getUseAccount());
                        editor.putString("useName",account.getUseName());
                        editor.putString("useLastN",account.getUseLastN());
                        editor.putString("useCorre",account.getUseCorre());
                        editor.commit();
                    }

                    if(aux!=-1 && aux!=0) goHome();
                    if(aux==0){progreso.hide(); Toast.makeText(Login.this, getString(R.string.error_Login1), Toast.LENGTH_SHORT).show();
                    };
                    if(aux==-1){progreso.hide(); Toast.makeText(Login.this, getString(R.string.error_Login), Toast.LENGTH_SHORT).show();
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
//        Intent i = new Intent(Login.this, DetailTheme.class);
        startActivity(i);
    }
}