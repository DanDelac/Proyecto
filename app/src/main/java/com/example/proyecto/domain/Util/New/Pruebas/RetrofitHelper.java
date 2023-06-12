package com.example.proyecto.domain.Util.New.Pruebas;

import com.example.proyecto.domain.Util.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Util.RUTA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
