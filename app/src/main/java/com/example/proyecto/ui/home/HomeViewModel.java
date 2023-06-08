package com.example.proyecto.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStore;

import com.example.proyecto.Entidades.Unit;
import com.example.proyecto.Util.New.GetQuotesUseCase;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Unit> unitMutableLiveData = new MutableLiveData<Unit>();

    public void onCreate(){
        GetQuotesUseCase getQuotesUseCase = new GetQuotesUseCase();
        List<Unit> result = getQuotesUseCase.invoke();

        if (!(result == null)){
            unitMutableLiveData.postValue(result.get(0));
        }
    }
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}