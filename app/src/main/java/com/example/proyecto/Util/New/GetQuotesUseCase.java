package com.example.proyecto.Util.New;

import com.example.proyecto.Entidades.Unit;

import java.util.List;

public class GetQuotesUseCase {

    private QuoteRepository repository = new QuoteRepository();

    public List<Unit> invoke(){
        return repository.getAllQuotes();
    }
}
