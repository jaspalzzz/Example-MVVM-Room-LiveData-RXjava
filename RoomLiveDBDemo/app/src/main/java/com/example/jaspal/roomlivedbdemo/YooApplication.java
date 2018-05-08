package com.example.jaspal.roomlivedbdemo;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.jaspal.roomlivedbdemo.db.AppDatabase;

public class YooApplication extends Application {

    public static YooApplication instance;
    public AppDatabase appDatabase;
    public ViewModelProvider.NewInstanceFactory provider;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "appdatabase").build();
        provider=new ViewModelProvider.AndroidViewModelFactory(this);
    }

    public  AppDatabase getDbHandler() {
        return appDatabase;
    }

    public ViewModelProvider.NewInstanceFactory getViewModelProvider()
    {
        return provider;
    }


    public static YooApplication getInstance() {
        return instance;
    }
}
