package com.prog4.appcocinero.data

data class Insumo(
    val id: Int,
    val nombre: String,
    val categoria: String,
    var disponible: Boolean
)