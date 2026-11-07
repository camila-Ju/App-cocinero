package com.prog4.appcocinero.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Store
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreen(val route: String, val title: String, val icon: ImageVector) {
    object Comandas : AppScreen("comandas", "Comandas", Icons.Default.Home)
    object Produccion : AppScreen("produccion", "Producción", Icons.Default.List)
    object Insumos : AppScreen("insumos", "Insumos", Icons.Default.Store)
    object Perfil : AppScreen("perfil", "Perfil", Icons.Default.Person)

    // Pantallas que no van en la barra inferior pero forman parte de la app
    object Login : AppScreen("login", "Login", Icons.Default.Home)
    object ComandaDetalle : AppScreen("comanda_detalle", "Detalle", Icons.Default.Home)
}