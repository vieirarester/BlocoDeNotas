package com.example.bloconotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseSingleton databaseSingleton = DatabaseSingleton.getInstance(this);

        setContentView(R.layout.activity_main);
    }

}