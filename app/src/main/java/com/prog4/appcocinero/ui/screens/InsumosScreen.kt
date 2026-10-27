package com.prog4.appcocinero.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Insumo(
    val id: Int,
    val nombre: String,
    val categoria: String,
    var disponible: Boolean
)

val listaInsumosIniciales = listOf(
    Insumo(1, "Carne para hamburguesa", "Carnes", true),
    Insumo(2, "Milanesa de ternera", "Carnes", true),
    Insumo(3, "Salmón", "Pescados", true),
    Insumo(4, "Merluza", "Pescados", false),
    Insumo(5, "Papas", "Vegetales", true),
    Insumo(6, "Hongos", "Vegetales", false),
    Insumo(7, "Queso parmesano", "Lácteos", true)
)

@Composable
@Preview(showBackground = true)
fun InsumosScreen() {
    var insumos by remember { mutableStateOf(listaInsumosIniciales) }

    val disponiblesCount = insumos.count { it.disponible }
    val agotadosCount = insumos.count { !it.disponible }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Restooo.",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = " COCINA ",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Ícono de usuario",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Insumos",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Control de stock · $disponiblesCount disponibles · $agotadosCount agotados",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            val insumosPorCategoria = insumos.groupBy { it.categoria }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                insumosPorCategoria.forEach { (categoria, lista) ->
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = categoria,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${lista.size} insumos",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    items(lista) { insumo ->
                        InsumoItemCard(
                            insumo = insumo,
                            onCheckedChange = { nuevoEstado ->
                                insumos = insumos.map {
                                    if (it.id == insumo.id) it.copy(disponible = nuevoEstado) else it
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InsumoItemCard(insumo: Insumo, onCheckedChange: (Boolean) -> Unit) {
    val backgroundColor = if (insumo.disponible) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.errorContainer
    }

    val contentColor = if (insumo.disponible) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onErrorContainer
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = insumo.nombre,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = contentColor
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (insumo.disponible) "• Disponible" else "• Agotado",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (insumo.disponible) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }

            Switch(
                checked = insumo.disponible,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onErrorContainer,
                    uncheckedTrackColor = MaterialTheme.colorScheme.errorContainer
                )
            )
        }
    }
}