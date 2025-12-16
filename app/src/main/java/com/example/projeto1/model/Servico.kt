package com.example.projeto1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "servicos")
data class Servico(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val categoria: String,
    val descricao: String,
    val imagem: Int,
    val telefone: String,
    val imagemUri: String? = null
) : Serializable
