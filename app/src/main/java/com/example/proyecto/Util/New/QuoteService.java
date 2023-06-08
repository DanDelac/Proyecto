package com.example.proyecto.Util.New;

import com.example.proyecto.Entidades.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuoteService {

    private final Retrofit retrofit = RetrofitHelper.getRetrofit();

    public ArrayList<Unit> getQuotes(){
        ArrayList<Unit> units = new ArrayList<>();
//        Response<ArrayList<Unit>> response = RetrofitHelper.getRetrofit().create(Api.class).getAllQuotes();
        Call<ArrayList<Unit>> response = retrofit.create(Api.class).getAllQuotes();
        ArrayList<Unit> finalUnits = units;
        response.enqueue(new Callback<ArrayList<Unit>>() {
            @Override
            public void onResponse(Call<ArrayList<Unit>> call, Response<ArrayList<Unit>> responsee) {
                for (int i = 0; i>responsee.body().size(); i++){
                    finalUnits.add(responsee.body().get(i));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Unit>> call, Throwable t) {

            }
        });

//        units = response.body();
        if (units == null) units = null;

        return units;
    }
}
