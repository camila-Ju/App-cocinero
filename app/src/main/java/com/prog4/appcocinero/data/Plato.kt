package com.prog4.appcocinero.data

data class PlatoComanda(
    val id: Int,
    val numeroPlato: String,
    val nombre: String,
    val unidades: Int,
    val alertaAlergia: String? = null,
    val ingredientes: List<String>,
    val platoAdicional: String? = null,
    var listo: Boolean = false
)