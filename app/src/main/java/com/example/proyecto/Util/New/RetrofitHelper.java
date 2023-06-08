package com.example.proyecto.Util.New;

import static com.example.proyecto.Util.Util.RUTA;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(RUTA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
