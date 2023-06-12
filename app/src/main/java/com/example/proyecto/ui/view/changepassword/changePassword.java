package com.example.proyecto.ui.view.changepassword;

import static com.example.proyecto.domain.Util.Segurity.verifyPassword;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.data.model.VolleySingleton;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.domain.Util.Util;
import com.example.proyecto.databinding.ActivityChangePasswordBinding;
import com.example.proyecto.ui.view.login.Login;

import org.json.JSONObject;

public class changePassword extends AppCompatActivity {
    private ActivityChangePasswordBinding binding;

    public static final String LOG_PREF="log";
    String idUser,useAccount,useName,useLastN,useCorre;
    String aux,aux1;

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences preferences = getSharedPreferences(LOG_PREF,0);
        idUser = preferences.getString("idUser","nnn");
        useAccount = preferences.getString("useAccount","nnn");
        useName = preferences.getString("useName","nnn");
        useLastN = preferences.getString("useLastN","nnn");
        useCorre = preferences.getString("useCorre","nnn");

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        binding.txtChangepassUser.setText(useLastN+", "+useName);
        binding.txtChangepassAccount.setText(useAccount);
        binding.txtChangepassMail.setText(useCorre);

        binding.checkBoxShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    binding.edtchangepass1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    binding.edtchangepass2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    binding.edtchangepass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    binding.edtchangepass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        binding.buttonChangepassSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aux = binding.edtchangepass1.getText().toString().replace(" ","");
                aux1 = binding.edtchangepass2.getText().toString().replace(" ","");

                if(aux.isEmpty()||aux1.isEmpty())
                    Toast.makeText(changePassword.this, getString(R.string.error_Null), Toast.LENGTH_SHORT).show();
                else {
                    if (verifyPassword(aux)) {
                        if (aux.equals(aux1)) {
                            dialogAcept();
                        } else
                            Toast.makeText(changePassword.this, getString(R.string.error_Pass), Toast.LENGTH_SHORT).show();
                    } else {
                        binding.txtSegPass.setVisibility(View.VISIBLE);
                        Toast.makeText(changePassword.this, getString(R.string.error_segPass), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.buttonChangepassCancel.setOnClickListener(new View.OnClickListener() {
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
                Integer aux = response.optInt("msj");
                String msj=null;
                if (aux==0) msj=getString(R.string.error_msj3);
                if (aux==1){
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