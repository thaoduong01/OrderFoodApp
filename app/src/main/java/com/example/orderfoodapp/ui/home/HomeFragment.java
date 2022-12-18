package com.example.orderfoodapp.ui.home;

import static java.lang.Float.parseFloat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.adapters.FoodAdapter;
import com.example.orderfoodapp.adapters.HomeHorAdapter;
import com.example.orderfoodapp.databinding.FragmentHomeBinding;
import com.example.orderfoodapp.db.DBHelper;
import com.example.orderfoodapp.adapters.SwipeItemFood;
import com.example.orderfoodapp.models.Food;
import com.example.orderfoodapp.models.HomeHorModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView rcvH, rcvV;
    List<HomeHorModel> homeHorModels;
    HomeHorAdapter homeHorAdapter;

//    List<HomeVerModel> homeVerModels;
//    HomeVerAdapter homeVerAdapter;

    DBHelper db;
    List<Food> listFood;
    RecyclerView.Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        rcvH = root.findViewById(R.id.home_hor_rec);
        rcvV = root.findViewById(R.id.home_ver_rec);

        /////Horizantal RCV
        homeHorModels = new ArrayList<>();

        homeHorModels.add(new HomeHorModel(R.drawable.pizza, "Pizza"));
        homeHorModels.add(new HomeHorModel(R.drawable.hamburger, "Burger"));
        homeHorModels.add(new HomeHorModel(R.drawable.fries, "Fries"));
        homeHorModels.add(new HomeHorModel(R.drawable.ice_cream, "Ice Cream"));
        homeHorModels.add(new HomeHorModel(R.drawable.bread, "Sandwich"));
        homeHorModels.add(new HomeHorModel(R.drawable.chicken, "Frie Chicken"));

        homeHorAdapter = new HomeHorAdapter(getActivity(), homeHorModels);
        rcvH.setAdapter(homeHorAdapter);
        rcvH.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rcvH.setHasFixedSize(true);
        rcvH.setNestedScrollingEnabled(false);


        /////Vertical RCV
//        homeVerModels = new ArrayList<>();
//        homeVerModels.add(new HomeVerModel(R.drawable.pizza1, "Pizza", "10:00-23:00", "4.7", "Min - 89K"));
//        homeVerModels.add(new HomeVerModel(R.drawable.burger1, "Burger", "10:00-23:00", "4.7", "Min - 89K"));
//        homeVerModels.add(new HomeVerModel(R.drawable.fries1, "Fries", "10:00-23:00", "4.7", "Min - 89K"));
//        homeVerModels.add(new HomeVerModel(R.drawable.icecream1, "Ice Cream", "10:00-23:00", "4.7", "Min - 89K"));
//
//        homeVerAdapter = new HomeVerAdapter(getActivity(), homeVerModels);
//        rcvV.setAdapter(homeVerAdapter);
//        rcvV.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
//        rcvV.setHasFixedSize(true);
//        rcvV.setNestedScrollingEnabled(false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(getContext());
        RecyclerView rcvFood = view.findViewById(R.id.home_ver_rec);
        listFood = db.getAllFood();
        adapter = new FoodAdapter(listFood, db);
        rcvFood.setAdapter(adapter);
        rcvFood.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        rcvFood.addItemDecoration(itemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new
                SwipeItemFood((FoodAdapter) adapter));
        itemTouchHelper.attachToRecyclerView(rcvFood);

        view.findViewById(R.id.floatingAddFood).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewDialog = LayoutInflater.from(getActivity()).inflate(R.layout.add_food, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(viewDialog);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                EditText edtMaMon = viewDialog.findViewById(R.id.dialogInputMa);
                EditText edtTenMon= viewDialog.findViewById(R.id.dialogInputTen);
                EditText edtRating = viewDialog.findViewById(R.id.dialogInputRating);
                EditText edtPrice= viewDialog.findViewById(R.id.dialogInputPrice);


                viewDialog.findViewById(R.id.dialogSaveFood).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Food f = new Food();
                        f.id = Integer.parseInt(edtMaMon.getText().toString().trim());
                        f.name = edtTenMon.getText().toString().trim();
                        f.price = parseFloat(edtPrice.getText().toString().trim());
                        f.rating = edtRating.getText().toString().trim();

                        db.InsertFood(f);

                        listFood.add(f);
                        adapter.notifyItemInserted(listFood.size() - 1);

                        Toast.makeText(getContext(), "Lưu khoa thành công!", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

            }
        });


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}