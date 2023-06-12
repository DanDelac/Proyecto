package com.example.proyecto.domain.Util.New.Pruebas;

import com.example.proyecto.data.model.Unit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("prueba.php?Cod=1")
    Call<ArrayList<Unit>> getAllQuotes();
}
