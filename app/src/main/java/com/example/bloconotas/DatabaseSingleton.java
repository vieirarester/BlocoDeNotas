package com.example.bloconotas;

import android.content.Context;

import androidx.room.Room;

public class DatabaseSingleton {

    private static DatabaseSingleton databaseSingleton;
    public AppDatabase appDatabase;

    private DatabaseSingleton(){

    }

    public static DatabaseSingleton getInstance(Context contexto){
        if(databaseSingleton == null){
            databaseSingleton = new DatabaseSingleton();
            databaseSingleton.appDatabase = Room.databaseBuilder(contexto, AppDatabase.class, "banco-notas").allowMainThreadQueries().build();
        }
        return databaseSingleton;
    }
}
