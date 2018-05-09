package com.example.jaspal.roomlivedbdemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.jaspal.roomlivedbdemo.db.FoodDao;
import com.example.jaspal.roomlivedbdemo.db.FoodYum;

@Database(entities = {FoodYum.class} , version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodDao getfoodDao();
}
