package com.prog4.appcocinero.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.filled.More
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.prog4.appcocinero.ui.screens.*

@Immutable
//se definen las claves simples e inmutables para el acceso a las pantallas
sealed interface ScreenKey {
    data object Perfil : ScreenKey
    data object Insumo : ScreenKey
    data object Login : ScreenKey
    data object Produccion : ScreenKey
    data object Comanda : ScreenKey
    data class ComandaDetail(val comandaId: Int) : ScreenKey
}

@Composable
fun AppNavigation() {
    // 2. Creación de la pila de navegación observable con la pantalla inicial (Login)
    val backStack = remember { mutableStateListOf<Any>(ScreenKey.Login) }

    // Obtener la clave de la pantalla activa en la cima de la pila
    val currentKey = backStack.lastOrNull()

// 2. Control de visibilidad: Ocultar la barra en pantallas como Login
    val showBottomBar = currentKey != null && currentKey !is ScreenKey.Login

    // 3. Contenedor Scaffold raíz para reutilizar la barra en todas las pantallas
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    // Opción: Comandas
                    NavigationBarItem(
                        selected = currentKey is ScreenKey.Comanda || currentKey is ScreenKey.ComandaDetail,
                        onClick = {
                            if (currentKey !is ScreenKey.Comanda) {
                                backStack.clear()
                                backStack.add(ScreenKey.Comanda)
                            }
                        },
                        icon = {
                            Icon(
                                Icons.AutoMirrored.Filled.ListAlt,
                                contentDescription = "Comandas"
                            )
                        },
                        label = { Text("Comandas") }
                    )

                    //produccion
                    NavigationBarItem(
                        selected = currentKey is ScreenKey.Produccion,
                        onClick = {
                            if (currentKey !is ScreenKey.Produccion) {
                                backStack.clear()
                                backStack.add(ScreenKey.Produccion)
                            }
                        },
                        icon = {
                            Icon(
                                Icons.Default.Fastfood,
                                contentDescription = "Produccion"
                            )
                        },
                        label = { Text("Produccion") }
                    )

                    // Opción: Insumos
                    NavigationBarItem(
                        selected = currentKey is ScreenKey.Insumo,
                        onClick = {
                            if (currentKey !is ScreenKey.Insumo) {
                                backStack.clear()
                                backStack.add(ScreenKey.Insumo)
                            }
                        },
                        icon = { Icon(Icons.Default.Inventory, contentDescription = "Insumos") },
                        label = { Text("Insumos") }
                    )

                    // Opción: Perfil
                    NavigationBarItem(
                        selected = currentKey is ScreenKey.Perfil,
                        onClick = {
                            if (currentKey !is ScreenKey.Perfil) {
                                backStack.clear()
                                backStack.add(ScreenKey.Perfil)
                            }
                        },
                        icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Perfil") },
                        label = { Text("Perfil") }
                    )
                }
            }
        }
    ) { paddingValues ->
        // 4. NavDisplay observa el backStack y renderiza la pantalla correspondiente dentro del padding del Scaffold

        // 3. Renderizado automático observando el backStack
        NavDisplay(
            modifier = Modifier.padding(paddingValues),
            backStack = backStack,
            onBack = {
                // Elimina el elemento superior de la pila si hay más de una pantalla
                if (backStack.size > 1) {
                    backStack.removeLastOrNull()
                }
            },
            entryProvider = { key ->
                // 4. Mapeo entre claves (ScreenKey) y objetos NavEntry con su Composable
                when (key) {
                    is ScreenKey.Login -> {
                        NavEntry(key) {
                            LoginScreen(
                                onLoginSuccess = {
                                    // Al autenticar, reemplazamos el Login por el menú/comandas principales
                                    backStack.clear()
                                    backStack.add(ScreenKey.Comanda)
                                }
                            )
                        }
                    }

                    is ScreenKey.Comanda -> {
                        NavEntry(key) {
                            // Ejemplo: ComandaScreen(onNavigateToDetail = { id -> backStack.add(ScreenKey.ComandaDetail(id)) })
                            ComandasScreen(
                                onComandaClick = { ComandaId ->
                                    // Agregamos la clave con parámetro a la pila (Push)
                                    backStack.add(ScreenKey.ComandaDetail(ComandaId))
                                }
                            )
                        }
                    }

                    is ScreenKey.ComandaDetail -> {
                        NavEntry(key) {
                            ComandaDetailScreen(
                                comandaId = key.comandaId,
                                onBack = {
                                    // Regresa a la pantalla anterior al hacer clic en el botón de volver
                                    if (backStack.size > 1) {
                                        backStack.removeLastOrNull()
                                    }
                                }
                            )
                        }
                    }

                    is ScreenKey.Produccion -> {
                        NavEntry(key) {
                            ProduccionScreen()
                        }
                    }

                    is ScreenKey.Insumo -> {
                        NavEntry(key) {
                            InsumosScreen()
                        }
                    }

                    is ScreenKey.Perfil -> {
                        NavEntry(key) {
                            PerfilScreen(
                                onCerrarSesion = {
                                    // Limpiamos todo el stack y volvemos a la pantalla de Login
                                    backStack.clear()
                                    backStack.add(ScreenKey.Login)
                                }
                            )
                        }
                    }

                    else -> NavEntry(Unit) {
                        Text(text = "Ruta no válida: $key")
                    }
                }
            }
        )
    }
}


