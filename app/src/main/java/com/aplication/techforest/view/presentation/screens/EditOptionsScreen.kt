package com.aplication.techforest.view.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun EditOptionsScreen(
    id : Int,
    
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        OutlinedTextField(
            label = { Text(text = "Humedad Maxima")},
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxSize()
        )

        OutlinedTextField(
            label = { Text(text = "Humedad Minima")},
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Button(enabled = false, onClick = {}){
            Text(text = "Guardar")
        }
    }
}