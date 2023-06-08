package com.example.proyecto.ui.evaluation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto.Entidades.QuestionsList;
import com.example.proyecto.Entidades._Session;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.Util.Util;
import com.example.proyecto.databinding.ActivityEvaluacionBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class _Evaluacion extends AppCompatActivity {
    private ActivityEvaluacionBinding binding;
    String idSes;
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
        binding=ActivityEvaluacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences(EVAL_SES,0);
        idSes = preferences.getString("idSes","nnn");
//        Toast.makeText(this, "URL: "+idSes, Toast.LENGTH_SHORT).show();
        Log.e("URL",idSes);
        config();
    }

    private void config() {
        requestQueue= Volley.newRequestQueue(_Evaluacion.this);
        CargarPreguntas();
        startTimer(binding.timer);

        binding.btnBackE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTime.purge();
                quizTime.cancel();
                startActivity(new Intent(_Evaluacion.this, MainActivity.class));
                finish();
            }
        });

        binding.btnOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = binding.btnOpcion1.getText().toString();
                    binding.btnOpcion1.setBackgroundResource(R.drawable.round_bg_red);
                    binding.btnOpcion1.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        binding.btnOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = binding.btnOpcion2.getText().toString();
                    binding.btnOpcion2.setBackgroundResource(R.drawable.round_bg_red);
                    binding.btnOpcion2.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        binding.btnOpcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = binding.btnOpcion3.getText().toString();
                    binding.btnOpcion3.setBackgroundResource(R.drawable.round_bg_red);
                    binding.btnOpcion3.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        binding.btnOpcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = binding.btnOpcion4.getText().toString();
                    binding.btnOpcion4.setBackgroundResource(R.drawable.round_bg_red);
                    binding.btnOpcion4.setTextColor(Color.parseColor("#FFFFFFFF"));

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
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
            binding.btnNext.setText("Submit Quiz");
        }

        if (currentQuestionPosition < questionsLists.size()){
            selectedOptionByUser = "";

            binding.btnOpcion1.setBackgroundResource(R.drawable.round_bg_write_stroke);
            binding.btnOpcion1.setTextColor(Color.parseColor("#1F6BB8"));

            binding.btnOpcion2.setBackgroundResource(R.drawable.round_bg_write_stroke);
            binding.btnOpcion2.setTextColor(Color.parseColor("#1F6BB8"));

            binding.btnOpcion3.setBackgroundResource(R.drawable.round_bg_write_stroke);
            binding.btnOpcion3.setTextColor(Color.parseColor("#1F6BB8"));

            binding.btnOpcion4.setBackgroundResource(R.drawable.round_bg_write_stroke);
            binding.btnOpcion4.setTextColor(Color.parseColor("#1F6BB8"));

            binding.questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
            binding.question.setText((questionsLists.get(currentQuestionPosition).getQuestion()));
            binding.btnOpcion1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            binding.btnOpcion2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            binding.btnOpcion3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            binding.btnOpcion4.setText(questionsLists.get(currentQuestionPosition).getOption4());
            Picasso.get().load(questionsLists.get(currentQuestionPosition).getExeImg())
                    .resize(200,250)
                    .into(binding.imvEval);
        }else {
            quizTime.purge();
            quizTime.cancel();

            SharedPreferences eval = getSharedPreferences(EVAL_SES,0);
            SharedPreferences.Editor editor = eval.edit();
            editor.putFloat("correct",getCorrectAnswer());
            editor.putFloat("incorrect",getInCorrectAnswer());
            editor.putFloat("total",questionsLists.size());
            editor.commit();
            Intent intent = new Intent(_Evaluacion.this, Resultado.class);
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

                    SharedPreferences eval = getSharedPreferences(EVAL_SES,0);
                    SharedPreferences.Editor editor = eval.edit();
                    editor.putFloat("correct",getCorrectAnswer());
                    editor.putFloat("incorrect",getInCorrectAnswer());
                    editor.putFloat("total",questionsLists.size());
                    editor.commit();
                    Intent intent = new Intent(_Evaluacion.this, Resultado.class);
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

        int IncorrectAnwer = 0;

        for (int i=0;i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAsnwer();

            if (!getUserSelectedAnswer.equals(getAnswer)){
                IncorrectAnwer++;
            }
        }
        return IncorrectAnwer;
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

        if (binding.btnOpcion1.getText().toString().equals(getAnswer)){
            binding.btnOpcion1.setBackgroundResource(R.drawable.round_bg_green_2);
            binding.btnOpcion1.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else if (binding.btnOpcion2.getText().toString().equals(getAnswer)) {
            binding.btnOpcion2.setBackgroundResource(R.drawable.round_bg_green_2);
            binding.btnOpcion2.setTextColor(Color.parseColor("#FFFFFFFF"));
        }else if (binding.btnOpcion3.getText().toString().equals(getAnswer)) {
            binding.btnOpcion3.setBackgroundResource(R.drawable.round_bg_green_2);
            binding.btnOpcion3.setTextColor(Color.parseColor("#FFFFFFFF"));
        }else if (binding.btnOpcion4.getText().toString().equals(getAnswer)) {
            binding.btnOpcion4.setBackgroundResource(R.drawable.round_bg_green_2);
            binding.btnOpcion4.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
    }

    private void CargarPreguntas(){

        questionsLists = new ArrayList<>();
        questionsLists.clear();

        String url = Util.RUTA+idSes;
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONArray jsonArray = response.optJSONArray("tblExerc");
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
                        binding.questions.setText((currentQuestionPosition+1)+"/"+questionsLists.size());
                        binding.question.setText((questionsLists.get(0).getQuestion()));
                        binding.btnOpcion1.setText(questionsLists.get(0).getOption1());
                        binding.btnOpcion2.setText(questionsLists.get(0).getOption2());
                        binding.btnOpcion3.setText(questionsLists.get(0).getOption3());
                        binding.btnOpcion4.setText(questionsLists.get(0).getOption4());
                        Picasso.get().load(questionsLists.get(0).getExeImg())
                                .resize(200,250)
                                .into(binding.imvEval);
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
