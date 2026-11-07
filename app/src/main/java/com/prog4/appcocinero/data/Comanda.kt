package com.prog4.appcocinero.data

data class Comanda(
    val id: Int,
    val mesa: String,
    val platos: List<String>,
    val estado: String // Ej: "Pendiente", "Preparando", "Listo"
)