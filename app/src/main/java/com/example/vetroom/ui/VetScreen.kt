package com.example.vetroom.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun VetScreen(viewModel: VetViewModel) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var selectedVetId by remember { mutableStateOf<Int?>(null) }

    val vetList by viewModel.vetList.collectAsState(initial = emptyList())
    val genders = listOf("Masculino", "Feminino", "Não Especificado")

    // Verifica se todos os campos estão preenchidos
    val isFormValid = name.isNotBlank() && type.isNotBlank() && level.isNotBlank() && gender.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF98c9a3))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Campos de entrada de Nome
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada Raça
        TextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Raça") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada Idade
        TextField(
            value = level,
            onValueChange = { level = it },
            label = { Text("Idade") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada Data
        DateTextField(
            value = date,
            onValueChange = { date = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de entrada Gênero
        TextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gênero") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Botão de adicionar ou atualizar consulta
        Button(
            onClick = {
                if (isFormValid) {
                    viewModel.addOrUpdateVet(selectedVetId, name, type, level.toIntOrNull() ?: 1, date, gender)
                    name = ""
                    type = ""
                    level = ""
                    date = ""
                    gender = ""
                    selectedVetId = null
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            enabled = isFormValid
        ) {
            Text(if (selectedVetId == null) "Adicionar Consulta" else "Atualizar Consulta")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Exibição da lista de consultas
        Text("Consultas Agendadas:", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre os itens
        ) {
            items(vetList) { vet ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.7f), RoundedCornerShape(8.dp)) // Cor branca transparente com cantos arredondados
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Nome: ${vet.name}")
                        Text(text = "Raça: ${vet.race}")
                        Text(text = "Idade: ${vet.age}")
                        Text(text = "Data: ${vet.date}")
                        Text(text = "Gênero: ${vet.gender}")
                    }

                    Row {
                        // Botão de editar
                        IconButton(onClick = {
                            name = vet.name
                            type = vet.race
                            level = vet.age.toString()
                            date = vet.date
                            gender = vet.gender
                            selectedVetId = vet.id
                        }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar Consulta"
                            )
                        }

                        // Botão de excluir
                        IconButton(onClick = { viewModel.deleteVet(vet) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Excluir Consulta"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DateTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    // Formata a data ao receber um novo valor
    val formattedValue = remember(value) { formatDate(value) }

    TextField(
        value = formattedValue,
        onValueChange = { newValue ->
            // Remove caracteres não numéricos e limita a 8 dígitos
            val cleanString = newValue.replace("[^\\d]".toRegex(), "")
            val limitedString = cleanString.take(8)
            // Atualiza o valor formatado
            onValueChange(formatDate(limitedString))
        },
        label = { Text("Data") },
        placeholder = { Text("Digite a data") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxLines = 1
    )
}