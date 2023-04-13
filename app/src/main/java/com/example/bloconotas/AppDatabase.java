package com.example.bloconotas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Nota.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NotaDao notaDao();
}
