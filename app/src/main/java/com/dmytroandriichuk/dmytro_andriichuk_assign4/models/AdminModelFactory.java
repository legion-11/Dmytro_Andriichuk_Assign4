package com.dmytroandriichuk.dmytro_andriichuk_assign4.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//ability to create viewModel With parameters
public class AdminModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mParam;


    public AdminModelFactory(Application application, int param) {
        mApplication = application;
        mParam = param;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AdminsOrderModel(mApplication, mParam);
    }
}
