package com.example.jaspal.roomlivedbdemo.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.jaspal.roomlivedbdemo.db.FoodYum;
import com.example.jaspal.roomlivedbdemo.YooApplication;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class MainViewModel extends ViewModel {
    LiveData<List<FoodYum>> foodYumList;

    public void loadYumFoodData() {
        foodYumList = YooApplication.getInstance().getDbHandler().getfoodDao().getFoodList();
    }

    public LiveData<List<FoodYum>> getYumFoodList() {
        loadYumFoodData();
        return foodYumList;
    }

    public Completable addFoodYumm(Context context, String title, String price, String quantity) {
        final FoodYum food = new FoodYum();
        food.id = getUniqueId(context);
        food.price = price;
        food.title = title;
        food.quantity = quantity;
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    YooApplication.getInstance().getDbHandler().getfoodDao().addFoodItems(food);
                    emitter.onComplete();
                }
            }
        });
    }

    public int getUniqueId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("id", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        id = id + 100;
        sharedPreferences.edit().putInt("id", id).apply();
        return id;
    }
}
