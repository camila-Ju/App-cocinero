package com.prog4.appcocinero.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.prog4.appcocinero.data.TareaProduccion

class ProduccionViewModel : ViewModel() {
    var tareas by mutableStateOf(
        listOf(
            TareaProduccion(1, "Hornear pan de hamburguesa", "Panadería", "24 unidades", true),
            TareaProduccion(2, "Porcionar pan de mesa", "Panadería", "20 porciones", true),
            TareaProduccion(3, "Lavar hojas de ensalada", "Vegetales", "2 kg", true),
            TareaProduccion(4, "Cortar papas rústicas", "Vegetales", "5 kg", false),
            TareaProduccion(5, "Preparar vegetales asados", "Vegetales", "3 kg", false),
            TareaProduccion(6, "Porcionar salmón", "Proteínas", "12 porciones de 200 g", true),
            TareaProduccion(7, "Armar hamburguesas", "Proteínas", "24 unidades de 180 g", false)
        )
    )
        private set

    val totalTareas: Int
        get() = tareas.size

    val completadasCount: Int
        get() = tareas.count { it.completada }

    val porcentajeProgreso: Float
        get() = if (totalTareas > 0) (completadasCount.toFloat() / totalTareas) else 0f

    fun toggleTareaCompletada(idTarea: Int) {
        tareas = tareas.map {
            if (it.id == idTarea) it.copy(completada = !it.completada) else it
        }
    }
}