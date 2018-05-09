package com.example.jaspal.roomlivedbdemo.view.adapter;

import android.databinding.DataBindingUtil;
import android.renderscript.ScriptGroup;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jaspal.roomlivedbdemo.R;
import com.example.jaspal.roomlivedbdemo.databinding.FoodItemBinding;
import com.example.jaspal.roomlivedbdemo.db.FoodYum;
import com.example.jaspal.roomlivedbdemo.view.activity.MainActivity;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.Holder> {

    ArrayList<FoodYum> list;

    public FoodListAdapter(ArrayList<FoodYum> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        FoodItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.food_item,parent,false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.binding.setItem(list.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        FoodItemBinding binding;
        public Holder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
