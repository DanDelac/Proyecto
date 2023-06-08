package com.example.proyecto.Util.New;

import com.example.proyecto.Entidades.Unit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface Api {
    @GET("prueba.php?Cod=1")
    Call<ArrayList<Unit>> getAllQuotes();
}
