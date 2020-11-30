package com.dmytroandriichuk.dmytro_andriichuk_assign4.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//model with parameters
public class CustomerModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mParam;


    public CustomerModelFactory(Application application, int param) {
        mApplication = application;
        mParam = param;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CustomerModel(mApplication, mParam);
    }
}
