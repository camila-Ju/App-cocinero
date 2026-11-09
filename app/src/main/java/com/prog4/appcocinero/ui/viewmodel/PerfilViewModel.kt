package com.prog4.appcocinero.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PerfilViewModel : ViewModel() {
    var nombreUsuario by mutableStateOf("Lucía Martínez")
        private set

    var turnoSeleccionado by mutableStateOf("Noche · 18:00–02:00")
        private set

    fun onNombreChanged(nuevoNombre: String) {
        nombreUsuario = nuevoNombre
    }

    fun guardarCambios(onSaved: () -> Unit) {
        // Acá harías la lógica para guardar en Base de Datos (Room) o API
        onSaved()
    }
}