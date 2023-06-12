package com.example.proyecto.domain.Util.New.Pruebas;

import com.example.proyecto.data.model.Unit;

import java.util.List;

public class QuoteRepository {

    private QuoteService quoteService = new QuoteService();

    public List<Unit> getAllQuotes(){

        List<Unit> response = quoteService.getQuotes();
        QuoteProvider.quotes = response;

        return response;
    }
}
