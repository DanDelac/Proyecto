package com.example.proyecto.ui.RecoverPass;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proyecto.Entidades.VolleySingleton;
import com.example.proyecto.R;
import com.example.proyecto.Util.RandomTextGenerator;
import com.example.proyecto.Util.SendMail;
import com.example.proyecto.Util.Util;
import com.example.proyecto.ui.Register.Register;
import com.example.proyecto.ui.login.Login;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecoverPass extends AppCompatActivity implements Response.ErrorListener,Response.Listener<JSONObject> {

    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest, jsonObjectRequest_2 ;

    String mensaje = null;
    String correo_ingresado=null;
    String nuevaClave;

    EditText edt_correo;
    Button btn_restablecer;
    Button btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_pass);

        configuracion();
    }

    private void configuracion() {
        edt_correo = findViewById(R.id.edt_correo);
        btn_restablecer = findViewById(R.id.btn_restablecer);
        btn_restablecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restablecer();
            }
        });
        btn_cancelar = findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancelar();
            }
        });
        requestQueue = VolleySingleton.getmInstance(RecoverPass.this).getRequestQueue();
    }

    private void Cancelar() {
        Intent e = new Intent(RecoverPass.this, Login.class);
        e.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(e);
    }

    private void Restablecer() {
        correo_ingresado = edt_correo.getText().toString();
        progreso = new ProgressDialog(this);
        progreso.setMessage(getString(R.string.seraching));
        progreso.show();
        if (!correo_ingresado.isEmpty()){
            //consultar existencia de correo
            String url = Util.RUTA+"accesoPass.php?Correo="+correo_ingresado;
            url=url.replace(" ","%20");
            jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            requestQueue.add(jsonObjectRequest);
        }
        else {
            progreso.hide();
            Toast.makeText(this, "Los campos no pueden quedar vacios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        Integer resultado = 0;
        JSONArray jsonArray = response.optJSONArray("tblUser");
        try {
            for (int i=0;i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                resultado = jsonObject.getInt("idUser");
            }
        }catch (Exception e){
            Toast.makeText(this, "Error, onResponse-accespPass: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
        Buscar_Usuario(resultado);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error, onErrorResponse: "+error.toString(), Toast.LENGTH_SHORT).show();
    }

    private void Buscar_Usuario(Integer id_Usuario) {
        if (id_Usuario==-1){
            mensaje = "No se encontro un usuario relacionado a este correo";
            AlertDialog("ALERTA",mensaje);
        }
        else {
            Actualizar_Clave(id_Usuario);
        }
    }

    private void Actualizar_Clave(Integer id_usuario){

        nuevaClave = RandomTextGenerator.generateRandomText();
        String url = Util.RUTA+"actualizarPass.php?Cod="+id_usuario+"&Pass="+nuevaClave;
        url=url.replace(" ","%20");
        jsonObjectRequest_2=new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Integer respuesta = 0;
                        try {
                            respuesta= response.getInt("msj");
                        }catch (Exception e){
                            Toast.makeText(RecoverPass.this,
                                    "Error, onResponse-actualizarPass: "+ e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        Confirmacion_Actualizacion(respuesta);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecoverPass.this,
                                "Error, onErrorResponse: "+error.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonObjectRequest_2);
    }

    private void Confirmacion_Actualizacion(Integer msj) {
        if (msj == 1){
            Enviar_Correo();
            goLogin();
        }
        else {
            mensaje="No se pudo actualizar su contraseña";
            AlertDialog("Error", mensaje);
        }
    }

    private void goLogin() {
        Intent i = new Intent(RecoverPass.this,Login.class);
        startActivity(i);
    }

    private void Enviar_Correo() {
        SendMail sm = new SendMail(this,
                correo_ingresado,
                "Su cuenta fue reestablecida correctamente",
                "Su contraseña para acceder ahora es: "+nuevaClave);
        sm.execute();

        mensaje="Se actualizo correctamente su contraseña";
        AlertDialog("Contraseña restablecida", mensaje);
    }

    private void AlertDialog(String titulo, String mensaje){
        progreso.hide();
        AlertDialog.Builder Guardar = new AlertDialog.Builder(RecoverPass.this);
        Guardar.setTitle(titulo);
        Guardar.setMessage(mensaje);
        Guardar.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
//        Guardar.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener(){
//            public void onClick(DialogInterface dialog, int which){
//                dialog.cancel();
//            }
//        });
        Guardar.show();
    }
}