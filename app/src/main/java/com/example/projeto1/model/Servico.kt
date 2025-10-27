package com.example.projeto1.model

import java.io.Serializable

data class Servico(
    val nome: String,
    val categoria: String,
    val descricao: String,
    val imagem: Int,
    val telefone: String
) : Serializable
