package com.example.proyecto.ui.view.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto.data.model.Unit;
import com.example.proyecto.domain.Util.New.Pruebas.GetQuotesUseCase;

import java.util.List;

public class HomeViewModel_2 extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Unit> unitMutableLiveData = new MutableLiveData<Unit>();

    public void onCreate(){
        GetQuotesUseCase getQuotesUseCase = new GetQuotesUseCase();
        List<Unit> result = getQuotesUseCase.invoke();

        if (!(result == null)){
            unitMutableLiveData.postValue(result.get(0));
        }
    }
    public HomeViewModel_2() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}