package com.example.orderfoodapp.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.adapters.FoodAdapter;
import com.example.orderfoodapp.models.Food;

public class SwipeItemFood extends ItemTouchHelper.SimpleCallback{

    FoodAdapter mAdapter;

    public SwipeItemFood(FoodAdapter adapter)
    {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        this.mAdapter.deleteItemAtPos(pos);
    }

}
