package com.prog4.appcocinero.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.prog4.appcocinero.data.Comanda

class ComandasViewModel : ViewModel() {
    var listaComandas by mutableStateOf(
        listOf(
            Comanda(1, "Mesa N° 1", listOf("1x Lomito", "1x Pizza"), "Pendiente"),
            Comanda(2, "Mesa N° 5", listOf("1x Hamburguesa", "1x Papas fritas"), "Preparando"),
            Comanda(3, "Mesa N° 3", listOf("2x Milanesas con puré"), "Pendiente")
        )
    )
        private set

    fun cambiarEstadoComanda(idComanda: Int, nuevoEstado: String) {
        listaComandas = listaComandas.map {
            if (it.id == idComanda) it.copy(estado = nuevoEstado) else it
        }
    }
}