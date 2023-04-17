package com.example.bloconotas;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotaDao {

    @Insert
    public void inserir(Nota nota);
    @Delete
    public void deletar(Nota nota);
    @Query("SELECT * FROM Nota")
    public List<Nota> listarTodos();

}
