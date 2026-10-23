package com.prog4.appcocinero.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Modelo de datos sencillo para representar una Comanda
data class Comanda(
    val id: Int,
    val mesa: String,
    val platos: List<String>,
    val estado: String // Ej: "Pendiente", "Preparando", "Listo"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun ComandasScreen() {
    // 2. Datos harcodeados simulando lo que vería el cocinero (inspirado en tus mockups)
    var listaComandas by remember {
        mutableStateOf(
            listOf(
                Comanda(1, "Mesa N° 1", listOf("1x Lomito", "1x Pizza"), "Pendiente"),
                Comanda(2, "Mesa N° 5", listOf("1x Hamburguesa", "1x Papas fritas"), "Preparando"),
                Comanda(3, "Mesa N° 3", listOf("2x Milanesas con puré"), "Pendiente")
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RestoCo - Panel de Cocina") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        // 3. LazyColumn funciona igual que un RecyclerView optimizado para listas largas
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Comandas Activas",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            items(listaComandas) { comanda ->
                TarjetaComanda(
                    comanda = comanda,
                    onCambiarEstado = { nuevoEstado ->
                        // Actualizamos el estado de la comanda seleccionada
                        listaComandas = listaComandas.map {
                            if (it.id == comanda.id) it.copy(estado = nuevoEstado) else it
                        }
                    }
                )
            }
        }
    }
}

// 4. Componente visual (Composable) para cada tarjeta de pedido
@Composable
fun TarjetaComanda(comanda: Comanda, onCambiarEstado: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Título de la mesa
            Text(
                text = comanda.mesa,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Listado de platos del pedido
            Text(text = "Platos:", style = MaterialTheme.typography.bodyMedium)
            comanda.platos.forEach { plato ->
                Text(text = "• $plato", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Estado actual y botones interactivos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Estado: ${comanda.estado}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = when(comanda.estado) {
                        "Listo" -> Color(0xFF2E7D32) // Verde
                        "Preparando" -> Color(0xFFEF6C00) // Naranja
                        else -> Color.Gray
                    }
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { onCambiarEstado("Preparando") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                    ) {
                        Text(
                            text ="Preparar",
                            color =Color.Black
                        )
                    }
                    /*Button(
                        onClick = { onCambiarEstado("Listo") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A))
                    ) {
                        Text("Listo")
                    }*/
                }
            }
        }
    }
}

@Composable
fun RestoCoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}