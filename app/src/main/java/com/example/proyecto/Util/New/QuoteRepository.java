package com.example.proyecto.Util.New;

import com.example.proyecto.Entidades.Unit;

import java.util.ArrayList;
import java.util.List;

public class QuoteRepository {

    private QuoteService quoteService = new QuoteService();

    public List<Unit> getAllQuotes(){

        List<Unit> response = quoteService.getQuotes();
        QuoteProvider.quotes = response;

        return response;
    }
}
