package com.example.proyecto.domain.Util.New.Pruebas;

import com.example.proyecto.data.model.Unit;

import java.util.List;

public class GetQuotesUseCase {

    private QuoteRepository repository = new QuoteRepository();

    public List<Unit> invoke(){
        return repository.getAllQuotes();
    }
}
