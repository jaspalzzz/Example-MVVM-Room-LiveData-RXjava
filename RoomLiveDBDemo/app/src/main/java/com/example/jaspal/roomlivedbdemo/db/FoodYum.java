package com.example.jaspal.roomlivedbdemo.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity
public class FoodYum {
    @PrimaryKey @NonNull
    @ColumnInfo(name = "id")
    public int id;
    public String title;
    public String price;
    public String quantity;
}
