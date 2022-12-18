package com.example.orderfoodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.orderfoodapp.MainActivity;
import com.example.orderfoodapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void loginActivity(View v){
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }

    public void registerActivity(View v){
        startActivity(new Intent(WelcomeActivity.this, RegisterActivity.class));
    }

    public void mainActivity(View v){
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
    }
}