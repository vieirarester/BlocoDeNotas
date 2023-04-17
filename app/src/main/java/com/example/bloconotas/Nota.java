package com.example.bloconotas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nota {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String titulo;
    @ColumnInfo
    String descricao;

    public Nota(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.titulo + '\'' + this.descricao + '\'';
    }
}
