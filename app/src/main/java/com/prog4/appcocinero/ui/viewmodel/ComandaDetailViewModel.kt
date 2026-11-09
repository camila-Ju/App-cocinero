package com.prog4.appcocinero.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.prog4.appcocinero.data.PlatoComanda

class ComandaDetailViewModel : ViewModel() {
    // Datos simulados para la Mesa 12 (puedes adaptarlo para recibir el id si cargas desde base de datos)
    var platos by mutableStateOf(
        listOf(
            PlatoComanda(
                id = 1,
                numeroPlato = "PLATO 1 DE 2",
                nombre = "Salmón a la plancha",
                unidades = 2,
                alertaAlergia = "ALERGIA: FRUTOS SECOS. Sin frutos secos. Usar utensilios limpios y evitar la contaminación cruzada.",
                ingredientes = listOf(
                    "Filete de salmón",
                    "Zucchini y zanahoria asados",
                    "Limón y perejil fresco",
                    "Aceite de oliva, sal y pimienta"
                ),
                platoAdicional = "1 × Ensalada de la casa",
                listo = false
            ),
            PlatoComanda(
                id = 2,
                numeroPlato = "PLATO 2 DE 2",
                nombre = "Pizza",
                unidades = 2,
                alertaAlergia = null,
                ingredientes = listOf(
                    "Masa madre", "Salsa de tomate natural", "Queso mozzarella", "Albahaca fresca"
                ),
                platoAdicional = "1 × Ensalada de la casa",
                listo = false
            )
        )
    )
        private set

    fun togglePlatoListo(idPlato: Int) {
        platos = platos.map {
            if (it.id == idPlato) it.copy(listo = !it.listo) else it
        }
    }
}