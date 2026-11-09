package com.prog4.appcocinero.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // Estados internos de los campos de texto
    var usuario by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var errorMensaje by mutableStateOf<String?>(null)
        private set

    fun onUsuarioChanged(nuevoUsuario: String) {
        usuario = nuevoUsuario
        errorMensaje = null // Limpiamos error al escribir
    }

    fun onPasswordChanged(nuevaPassword: String) {
        password = nuevaPassword
        errorMensaje = null
    }

    // Lógica para validar el ingreso
    fun validarLogin(onSuccess: () -> Unit) {
        val usuarioCorrecto = "lucia.martinez"
        val passwordCorrecta = "1234"

        if (usuario.trim().lowercase() == usuarioCorrecto && password.trim() == passwordCorrecta) {
            errorMensaje = null
            onSuccess()
        } else {
            errorMensaje = "Usuario o contraseña incorrectos"
        }
    }
}