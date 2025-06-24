package com.zurie.ejemplo2composeridgs903

import android.widget.RadioGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CalculadoraDosNumeros(){
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var rS by remember { mutableStateOf("") }
    var rR by remember { mutableStateOf("") }
    var rM by remember { mutableStateOf("") }
    var rV by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column (modifier = Modifier.
    fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        ){
        TextField(
            value = num1,
            onValueChange = {num1 = it},
            label = { Text("Numero 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = {num2 = it},
            label = { Text("Numero 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        val radioOpciones = listOf("Suma", "Resta", "Multiplicacion", "Division")
        var selectedOption by remember { mutableStateOf(radioOpciones[0]) }

        Column {
            radioOpciones.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { selectedOption = text },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Button(onClick = {
            val n1 = num1.toIntOrNull()
            val n2 = num2.toIntOrNull()
            resultado = if (n1 != null && n2 != null) {
                when (selectedOption) {
                    "Suma" -> "Resultado: ${n1 + n2}"
                    "Resta" -> "Resultado: ${n1 - n2}"
                    "Multiplicacion" -> "Resultado: ${n1 * n2}"
                    "Division" -> "Resultado: ${n1 / n2}"
                    else -> "Seleccione una operación válida"
                }
            } else {
                "Ingrese números válidos"
            }
        }) {
            Text("Calcular")
        }
        Text(text = resultado)
    }
}

