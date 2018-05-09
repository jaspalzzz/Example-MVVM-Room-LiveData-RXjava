package com.example.jaspal.roomlivedbdemo.db;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("select * from foodyum")
    LiveData<List<FoodYum>> getFoodList();

    @Insert
    void addFoodItems(FoodYum food);
}
