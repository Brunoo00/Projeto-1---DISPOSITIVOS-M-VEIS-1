package com.example.projeto1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.projeto1.model.Servico

@Dao
interface ServicoDao {
    @Query("SELECT * FROM servicos")
    fun getAll(): List<Servico>

    @Insert
    fun insert(servico: Servico)

    @Delete
    fun delete(servico: Servico)
}
