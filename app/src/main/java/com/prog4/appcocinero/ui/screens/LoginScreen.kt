package com.prog4.appcocinero.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prog4.appcocinero.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    onLoginSuccess: () -> Unit //se invoca este callback cuando la validación sea correcta
    ) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Restooo.",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Tu cocina te espera.",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Ingresá para comenzar tu turno.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))
            // aqui cambie
            OutlinedTextField(
                value = viewModel.usuario,
                onValueChange = { viewModel.onUsuarioChanged(it) },
                label = { Text("Usuario") },
                /*value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") },*/
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))
            //aqui cambie
            OutlinedTextField(
               /* value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },*/
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                   /* if (usuario == usuarioCorrecto && password == passwordCorrecta) {
                        Toast.makeText(context, "¡Bienvenido a la cocina!", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    } else {
                        Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }*/
                    //aqui cambie
                    viewModel.validarLogin(
                        onSuccess = {
                            Toast.makeText(context, "¡Bienvenido a la cocina!", Toast.LENGTH_SHORT).show()
                            onLoginSuccess()
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Entrar a cocina",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            //aqui cambie
            viewModel.errorMensaje?.let { error ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = error, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {})
}