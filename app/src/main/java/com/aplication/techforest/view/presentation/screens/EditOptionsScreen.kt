package com.aplication.techforest.view.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.utils.Resource
import com.aplication.techforest.viewmodel.EditOptionsScreenViewModel


@ExperimentalMaterialApi
@Composable
fun EditOptionsScreen(
    optionsId: Int,
    viewModel: EditOptionsScreenViewModel = hiltViewModel()
) {

    val optionsData =
        produceState<Resource<OptionDeviceResponse>>(initialValue = Resource.Loading()) {
            value = viewModel.getOptions(optionsId)
        }.value

    OptionsDataStateWrapper(optionsData = optionsData, viewModel = viewModel)

}


@ExperimentalMaterialApi
@Composable
fun OptionsDataStateWrapper(
    optionsData: Resource<OptionDeviceResponse>,
    loadingModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    viewModel: EditOptionsScreenViewModel
) {
    when (optionsData) {
        is Resource.Success -> {
            OptionsDataForm(optionsData = optionsData.data!!, viewModel = viewModel)
        }
        is Resource.Error -> {
            Text(
                text = optionsData.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary,
                        modifier = loadingModifier
                    )
                }
            }
        }
    }
}

@Composable
fun OptionsDataForm(
    optionsData: OptionDeviceResponse,
    viewModel: EditOptionsScreenViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    var data by remember { mutableStateOf(optionsData) }
    val context = LocalContext.current



    var humedadMaxima by remember { mutableStateOf("") }
    var humedadMinima by remember { mutableStateOf("") }
    var editOptionsData = optionsData

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .height(300.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = humedadMaxima,
            onValueChange = {
                humedadMaxima = it
            },
            //label = { Text(text = "Humedad Maxima, ${optionsData.valor_maximo}") },
            modifier = Modifier.weight(1F),
        )



        OutlinedTextField(
            label = { Text(text = "Humedad Minima, ${optionsData.valor_minimo}") },
            value = humedadMinima,
            onValueChange = {
                humedadMinima = it
            },
            modifier = Modifier.weight(1F)
        )

        Spacer(modifier = Modifier.padding(8.dp))
        Button(enabled = false, onClick = {
            data.valor_maximo = humedadMaxima.toInt()
            data.valor_minimo = humedadMinima.toInt()
            Log.d("OptionsData", "$data")
            viewModel.updateOptions(data.id, data)
            viewModel.myResponse.observe(lifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    Log.d("Options", response.body().toString())
                    Log.d("Options", response.code().toString())
                    Log.d("Options", response.message())
                }
                else{
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
                }
            })
        }) {
            Text(text = "Guardar")
        }
    }
}

@Composable
fun SaveDataStateWrapper(
    optionsData: Resource<OptionDeviceResponse>,
    loadingModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    viewModel: EditOptionsScreenViewModel
) {
    when (optionsData) {
        is Resource.Success -> {
            Log.d("Options Save", "Opciones guardadas ID: ${optionsData.data?.id}")
        }
        is Resource.Error -> {
            Text(
                text = optionsData.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary,
                        modifier = loadingModifier
                    )
                }
            }
        }
    }
}

