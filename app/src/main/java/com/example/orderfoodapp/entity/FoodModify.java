package com.example.orderfoodapp.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfoodapp.db.DBHelper;
import com.example.orderfoodapp.models.Food;

public class FoodModify {

    public static final String QUERY_CREATE_TABLE = "create table food (\n" +
            "\t_id integer primary key autoincrement,\n" +
            "\tname varchar(50),\n" +
            "\trating text,\n" +
            "\tprice float\n" +
            ")";

    public static Cursor findAll() {
        String sql = "select * from food";

        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        return cursor;
    }

    public static void insert(Food food) {
        SQLiteDatabase sqLiteDatabase = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", food.getName());
        values.put("rating", food.getRating());
        values.put("price", food.getPrice());

        sqLiteDatabase.insert("food", null, values);
    }
}
