package com.example.orderfoodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoodapp.R;
import com.example.orderfoodapp.db.DBHelper;
import com.example.orderfoodapp.models.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    Context context;
    List<Food> list;

    DBHelper db;

//    public FoodAdapter(Context context, List<Food> list) {
//        this.context = context;
//        this.list = list;
//    }


    public FoodAdapter(List<Food> list, DBHelper db) {
        this.list = list;
        this.db = db;
    }


    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.img.setImageResource(list.get(position).getImg());
//        holder.name.setText(list.get(position).getName());

        Food food = list.get(position);

        holder.name.setText(food.name);
        holder.price.setText(Float.toString(food.price));
        holder.rating.setText(food.rating);
        holder.category.setText(food.caterogy);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewDialogStudent = LayoutInflater.from(view.getContext()).inflate(R.layout.add_food, null);
                //custom DialogView
                AlertDialog.Builder builder = new AlertDialog.Builder(viewDialogStudent.getContext());
                builder.setView(viewDialogStudent); //custom dialog của Alert
                AlertDialog alert = builder.create();
                alert.show();

                //findViewbyId trên dialog để tìm ra EditText cho mã tên và giá

                EditText dialogInputMa = viewDialogStudent.findViewById(R.id.dialogInputMa);
                EditText dialogInputTen = viewDialogStudent.findViewById(R.id.dialogInputTen);
                EditText dialogInputRating = viewDialogStudent.findViewById(R.id.dialogInputRating);
                EditText dialogInputGia = viewDialogStudent.findViewById(R.id.dialogInputPrice);
                EditText dialogInputCategory = viewDialogStudent.findViewById(R.id.dialogInputCategory_edt);
                Spinner spinnerCategory = viewDialogStudent.findViewById(R.id.dialogInputCategory);

//

                dialogInputMa.setText(String.valueOf(food.id));
                dialogInputMa.setEnabled(false);
                dialogInputTen.setText(food.name);
                dialogInputRating.setText(food.rating);
                dialogInputGia.setText(Float.toString(food.price));
                dialogInputCategory.setText(food.caterogy);

                viewDialogStudent.findViewById(R.id.dialogSaveFood).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        food.name = dialogInputTen.getText().toString().trim();
                        food.rating = dialogInputRating.getText().toString().trim();
                        food.price = Float.parseFloat(dialogInputGia.getText().toString().trim());
                        food.caterogy = dialogInputCategory.getText().toString().trim();

                        db.UpdateFood(food);
                        notifyItemChanged(holder.getAdapterPosition());

                        Toast.makeText(viewDialogStudent.getContext(), "Thay đổi thông tin sinh viên thành công!", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, category, rating, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.txtcategory);
            rating = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
        }
    }

    public void deleteItemAtPos(int pos)
    {
        db.DeleteFood(list.get(pos));
        list.remove(pos);
        notifyItemChanged(pos);
    }
}
