package com.prog4.appcocinero.data

data class TareaProduccion(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val detalle: String,
    var completada: Boolean
)