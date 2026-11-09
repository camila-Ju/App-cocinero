package com.prog4.appcocinero.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.prog4.appcocinero.data.Insumo

class InsumosViewModel : ViewModel() {
    var insumos by mutableStateOf(
        listOf(
            Insumo(1, "Carne para hamburguesa", "Carnes", true),
            Insumo(2, "Milanesa de ternera", "Carnes", true),
            Insumo(3, "Salmón", "Pescados", true),
            Insumo(4, "Merluza", "Pescados", false),
            Insumo(5, "Papas", "Vegetales", true),
            Insumo(6, "Hongos", "Vegetales", false),
            Insumo(7, "Queso parmesano", "Lácteos", true)
        )
    )
        private set

    val disponiblesCount: Int
        get() = insumos.count { it.disponible }

    val agotadosCount: Int
        get() = insumos.count { !it.disponible }

    fun cambiarDisponibilidad(idInsumo: Int, nuevoEstado: Boolean) {
        insumos = insumos.map {
            if (it.id == idInsumo) it.copy(disponible = nuevoEstado) else it
        }
    }
}