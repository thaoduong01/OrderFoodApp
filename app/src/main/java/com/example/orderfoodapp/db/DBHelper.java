package com.example.orderfoodapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.orderfoodapp.entity.FoodModify;
import com.example.orderfoodapp.models.Food;
import com.example.orderfoodapp.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "OrderFoodApp";
    private static final int DB_VERSION = 1;

    //tao bang food
    public static final String TABLE_FOOD_NAME = "Food";
    public static final String COL_Food_ID = "FoodId";
    public static final String COL_Food_NAME = "FoodName";
    public static final String COL_Food_PRICE = "Price";
    public static final String COL_Food_RATING = "Rating";
    public static final String COL_Food_CATEROGY = "CategoryId";

    //tao bang caterogy
    public static final String TABLE_CATEROGY_NAME = "Category";
    public static final String COL_CATEROGY_ID = "CategoryId";
    public static final String COL_CATEROGY_NAME = "CategoryName";

    private static DBHelper instance = null;
    public synchronized static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

//        String sql = FoodModify.QUERY_CREATE_TABLE;
//
//        db.execSQL(sql);

        // Tạo bảng Food
        String query = "CREATE TABLE " + TABLE_FOOD_NAME + " ("
                + COL_Food_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_Food_NAME + " TEXT,"
                + COL_Food_PRICE + " TEXT,"
                + COL_Food_RATING + " TEXT,"
                + COL_Food_CATEROGY + " INTEGER)";
        db.execSQL(query);
        //Tạo bảng Category
        String query2 = "CREATE TABLE " + TABLE_CATEROGY_NAME + " ("+ COL_CATEROGY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CATEROGY_NAME + " TEXT)";
        db.execSQL(query2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xóa bảng Food
        String dropDB = "DROP TABLE IF EXISTS " + TABLE_FOOD_NAME;
        db.execSQL(dropDB);

        //xóa bảng Category
        String dropDB2 = "DROP TABLE IF EXISTS " + TABLE_CATEROGY_NAME;
        db.execSQL(dropDB2);
        onCreate(db);

    }



    //Food
    public List<Food> getAllFood()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Food> lst = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_FOOD_NAME;
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext())
        {
            Food food = new Food();
            food.id  = c.getInt(0);
            food.name = c.getString(1);
            food.price = c.getFloat(2);
            food.rating = c.getString(3);
            food.caterogy = c.getString(4);
            lst.add(food);
        }

        return lst;
    }

    public void InsertFood(Food food)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("INSERT INTO %s VALUES('%s', '%s','%f','%s', '%s')", TABLE_FOOD_NAME,
                food.id, food.name, food.price, food.rating, food.caterogy);
        db.execSQL(query);
    }

    public void UpdateFood(Food food)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("UPDATE %s SET %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = %d",
                TABLE_FOOD_NAME,
                COL_Food_NAME, food.name,
                COL_Food_PRICE, food.price,
                COL_Food_RATING, food.rating,
                COL_Food_CATEROGY, food.caterogy,
                COL_Food_ID, food.id);
        db.execSQL(query);
    }

    public void DeleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("DELETE FROM %s WHERE %s = %d", TABLE_FOOD_NAME, COL_Food_ID, food.getId());
        db.execSQL(query);
    }

}
