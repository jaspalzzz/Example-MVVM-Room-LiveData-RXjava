package com.example.jaspal.roomlivedbdemo.view.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jaspal.roomlivedbdemo.R;
import com.example.jaspal.roomlivedbdemo.databinding.ActivityMainBinding;
import com.example.jaspal.roomlivedbdemo.db.FoodYum;
import com.example.jaspal.roomlivedbdemo.view.adapter.FoodListAdapter;
import com.example.jaspal.roomlivedbdemo.view.callback.MainCallback;
import com.example.jaspal.roomlivedbdemo.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainCallback {

    ArrayList<FoodYum> list = new ArrayList<>();
    FoodListAdapter adapter;

    @Override
    public ActivityBinding getBindingActivity() {
        return new BaseActivity.ActivityBinding(R.layout.activity_main, MainViewModel.class);
    }

    @Override
    public void onCreateActivity(Bundle savedInstanceState) {
        binding.setMainclick(this);
        inflateList();
    }

    private void inflateList() {
        adapter = new FoodListAdapter(list);
        binding.foodListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.foodListView.setAdapter(adapter);
        getFoodList();
    }

    @Override
    public void addFoodYum(View view) {
        String title = binding.titleEt.getText().toString();
        String quantity = binding.quantity.getText().toString();
        String price = binding.price.getText().toString();
        viewModel.addFoodYumm(MainActivity.this, title, price, quantity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addFoodObserver);
    }

    public void getFoodList() {
        viewModel.getYumFoodList().observe(MainActivity.this, new Observer<List<FoodYum>>() {
            @Override
            public void onChanged(@Nullable List<FoodYum> foodYums) {
                for (FoodYum foodYum : foodYums) {
                    list.clear();
                    list.addAll(foodYums);
                    adapter.notifyDataSetChanged();
                    binding.foodListView.scrollToPosition(list.size()-1);
                }
            }
        });
    }

    private CompletableObserver addFoodObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(Disposable d) {
        }
        @Override
        public void onComplete() {
            Toast.makeText(MainActivity.this, "Data Added Successfully...", Toast.LENGTH_SHORT).show();
            clearEt();
        }
        @Override
        public void onError(Throwable e) {

        }
    };

    private void clearEt() {
        binding.price.setText("");
        binding.titleEt.setText("");
        binding.quantity.setText("");
    }
}
