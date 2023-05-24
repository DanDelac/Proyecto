package com.example.proyecto.ui.evaluation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto.Adapter.Adapter_Unit;
import com.example.proyecto.Entidades.QuestionsList;
import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.Entidades._Session;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;
import com.example.proyecto.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class _Evaluacion extends AppCompatActivity {
    String idSes;

    private TextView question, questions,timer;
    private Button btn_back_e ,btn_opcion1,btn_opcion2,btn_opcion3,btn_opcion4,btn_next;
    private ImageView imv_;
    private Timer quizTime;
    private int totalTimeInMins = 1, seconds = 0, currentQuestionPosition = 0;

    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private ArrayList<QuestionsList> questionsLists;

    private String selectedOptionByUser = "";

    private static final String EVAL_SES = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);

        SharedPreferences preferences = getSharedPreferences(EVAL_SES,0);
        idSes = preferences.getString("idSes","nnn");
//        Toast.makeText(this, "URL: "+idSes, Toast.LENGTH_SHORT).show();
        Log.e("URL",idSes);
        config();
    }

    private void config() {
        timer = findViewById(R.id.timer);

        btn_back_e = findViewById(R.id.btn_back_e);
        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        btn_opcion1 = findViewById(R.id.btn_opcion1);
        btn_opcion2 = findViewById(R.id.btn_opcion2);
        btn_opcion3 = findViewById(R.id.btn_opcion3);
        btn_opcion4 = findViewById(R.id.btn_opcion4);

        imv_=findViewById(R.id.imv_eval);

        btn_next =findViewById(R.id.btn_next);
        requestQueue= Volley.newRequestQueue(_Evaluacion.this);

        CargarPreguntas();
        startTimer(timer);

        btn_back_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTime.purge();
                quizTime.cancel();
                startActivity(new Intent(_Evaluacion.this, MainActivity.class));
                finish();
            }
        });

        btn_opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btn_opcion1.getText().toString();
                    btn_opcion1.setBackgroundResource(R.drawable.round_bg_red);
                    btn_opcion1.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        btn_opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btn_opcion2.getText().toString();
                    btn_opcion2.setBackgroundResource(R.drawable.round_bg_red);
                    btn_opcion2.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        btn_opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btn_opcion3.getText().toString();
                    btn_opcion3.setBackgroundResource(R.drawable.round_bg_red);
                    btn_opcion3.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        btn_opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btn_opcion4.getText().toString();
                    btn_opcion4.setBackgroundResource(R.drawable.round_bg_red);
                    btn_opcion4.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    Toast.makeText(_Evaluacion.this, "Por favor seleccione una opción", Toast.LENGTH_SHORT).show();
                } else {
                    changeNextQuestion();
                }
            }
        });
    }

    private void changeNextQuestion(){
        currentQuestionPosition++;

        if ((currentQuestionPosition+1) == questionsLists.size()){
            btn_next.setText("Submit Quiz");
        }

        if (currentQuestionPosition < questionsLists.size()){
            selectedOptionByUser = "";

            btn_opcion1.setBackgroundResource(R.drawable.round_bg_write_stroke);
            btn_opcion1.setTextColor(Color.parseColor("#1F6BB8"));

            btn_opcion2.setBackgroundResource(R.drawable.round_bg_write_stroke);
            btn_opcion2.setTextColor(Color.parseColor("#1F6BB8"));

            btn_opcion3.setBackgroundResource(R.drawable.round_bg_write_stroke);
            btn_opcion3.setTextColor(Color.parseColor("#1F6BB8"));

            btn_opcion4.setBackgroundResource(R.drawable.round_bg_write_stroke);
            btn_opcion4.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
            question.setText((questionsLists.get(currentQuestionPosition).getQuestion()));
            btn_opcion1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            btn_opcion2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            btn_opcion3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            btn_opcion4.setText(questionsLists.get(currentQuestionPosition).getOption4());
            Picasso.get().load(questionsLists.get(currentQuestionPosition).getExeImg())
                    .resize(200,250)
                    .into(imv_);
        }else {
            Intent intent = new Intent(_Evaluacion.this, Resultado.class);
            intent.putExtra("correct", getCorrectAnswer());
            intent.putExtra("incorrect", getInCorrectAnswer());
            startActivity(intent);

            finish();
        }
    }

    private void startTimer(TextView timerTextView){

        quizTime = new Timer();

        quizTime.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (seconds == 0 && totalTimeInMins == 1){
                    totalTimeInMins--;
                    seconds = 59;
                }
                else if (seconds == 0 && totalTimeInMins == 0){
                    quizTime.purge();
                    quizTime.cancel();

//                    Toast.makeText(_Evaluacion.this, "Se acabo el tiempo", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(_Evaluacion.this, Resultado.class);
                    intent.putExtra("correct",getCorrectAnswer());
                    intent.putExtra("incorrect",getInCorrectAnswer());
                    startActivity(intent);

                    finish();
                } else {
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if (finalMinutes.length() ==1){
                            finalMinutes = "0"+finalMinutes;
                        }
                        if (finalSeconds.length() == 1){
                            finalSeconds = "0"+finalSeconds;
                        }
                        timerTextView.setText(finalMinutes +":"+finalSeconds);
                    }
                });
            }
        },1000, 1000);
    }

    private int getCorrectAnswer(){

        int correctAnwer = 0;

        for (int i=0;i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAsnwer();

            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnwer++;
            }
        }
        return correctAnwer;
    }
    private int getInCorrectAnswer(){

        int correctAnwer = 0;

        for (int i=0;i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAsnwer();

            if (!getUserSelectedAnswer.equals(getAnswer)){
                correctAnwer++;
            }
        }
        return correctAnwer;
    }

    @Override
    public void onBackPressed() {
        quizTime.purge();
        quizTime.cancel();

        startActivity(new Intent(_Evaluacion.this, MainActivity.class));
        finish();
    }

    private void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionPosition).getAsnwer();

        if (btn_opcion1.getText().toString().equals(getAnswer)){
            btn_opcion1.setBackgroundResource(R.drawable.round_bg_green_2);
            btn_opcion1.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else if (btn_opcion2.getText().toString().equals(getAnswer)) {
            btn_opcion2.setBackgroundResource(R.drawable.round_bg_green_2);
            btn_opcion2.setTextColor(Color.parseColor("#FFFFFFFF"));
        }else if (btn_opcion3.getText().toString().equals(getAnswer)) {
            btn_opcion3.setBackgroundResource(R.drawable.round_bg_green_2);
            btn_opcion3.setTextColor(Color.parseColor("#FFFFFFFF"));
        }else if (btn_opcion4.getText().toString().equals(getAnswer)) {
            btn_opcion4.setBackgroundResource(R.drawable.round_bg_green_2);
            btn_opcion4.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
    }

    private void CargarPreguntas(){

        String idUser = null;
        questionsLists = new ArrayList<>();

        String url = Util.RUTA+idSes;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONArray jsonArray = response.optJSONArray("tblTheme");
                    QuestionsList Preguntas = null;
                    _Session _session = null;
                    try{
                        for (int i=0;i<jsonArray.length(); i++){
                            Preguntas = new QuestionsList();
                            JSONObject jsonObject=null;
                            jsonObject = jsonArray.getJSONObject(i);

                            Preguntas.setQuestion(jsonObject.getString("Pregunta"));
                            Preguntas.setOption1(jsonObject.getString("exeOpc1"));
                            Preguntas.setOption2(jsonObject.getString("exeOpc2"));
                            Preguntas.setOption3(jsonObject.getString("exeOpc3"));
                            Preguntas.setOption4(jsonObject.getString("exeOpc4"));
                            Preguntas.setAsnwer(jsonObject.getString("respuesta"));
                            Preguntas.setExeImg(jsonObject.getString("exeImg"));
                            questionsLists.add(Preguntas);
                        }
                        questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
                        question.setText((questionsLists.get(0).getQuestion()));
                        btn_opcion1.setText(questionsLists.get(0).getOption1());
                        btn_opcion2.setText(questionsLists.get(0).getOption2());
                        btn_opcion3.setText(questionsLists.get(0).getOption3());
                        btn_opcion4.setText(questionsLists.get(0).getOption4());
                        Picasso.get().load(questionsLists.get(0).getExeImg())
                                .resize(200,250)
                                .into(imv_);
                    }
                    catch (Exception e){

                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        requestQueue.add(jsonObjectRequest);
    }
}
