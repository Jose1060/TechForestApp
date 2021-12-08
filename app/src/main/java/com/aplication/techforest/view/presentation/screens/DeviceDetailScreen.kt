package com.aplication.techforest.view.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.model.Device.OptionDeviceResponse
import com.aplication.techforest.ui.theme.BabyPink
import com.aplication.techforest.utils.Resource
import com.aplication.techforest.viewmodel.DeviceDetailViewModel

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DeviceDetailScreen(
    viewModel: DeviceDetailViewModel = hiltViewModel(),
    deviceId: Int
) {
    val deviceData = produceState<Resource<DeviceResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getDeviceData(deviceId)
    }.value

    DeviceDataStateWrapper(deviceData = deviceData, viewModel = viewModel)

}


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DeviceDataStateWrapper(
    deviceData: Resource<DeviceResponse>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    viewModel: DeviceDetailViewModel
) {
    when (deviceData) {
        is Resource.Success -> {
            DeviceDataList(deviceData = deviceData.data!!, viewModel = viewModel)
        }
        is Resource.Error -> {
            Text(
                text = deviceData.message!!,
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

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun OptionsDeviceStateWrapper(
    optionsDevice: Resource<OptionDeviceResponse>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
) {
    when (optionsDevice) {
        is Resource.Success -> {
            OptionsDeviceList(optionsDevice.data!!)
        }
        is Resource.Error -> {
            Text(
                text = optionsDevice.message!!,
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

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun DeviceDataList(
    deviceData: DeviceResponse,
    viewModel: DeviceDetailViewModel
) {
    var expandedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(25.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Card(
                elevation = 15.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://images.pexels.com/photos/9604597/pexels-photo-9604597.jpeg"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Nombre del dispositivo:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(3f),
                )
                Text(
                    text = deviceData.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Estado: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = deviceData.estado,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Fecha de adquisicion:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = deviceData.fecha_adquisicion,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(1f)
                )
            }
            IconButton(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .fillMaxWidth()
                    .rotate(rotationState),
                onClick = { expandedState = !expandedState }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            if (expandedState) {
                val deviceOptions =
                    produceState<Resource<OptionDeviceResponse>>(initialValue = Resource.Loading()) {
                        value = viewModel.getDeviceOptions(deviceData.id)
                    }.value
                Box() {
                    OptionsDeviceStateWrapper(deviceOptions)
                }
            }
        }
    }
}

@Composable
fun OptionsDeviceList(
    OptionsDevice : OptionDeviceResponse
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Humedad:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(3f),
            )
            Text(
                text = "${OptionsDevice.humedad}%",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Humedad Maxima:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(3f),
            )
            Text(
                text = "${OptionsDevice.valor_maximo}%",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Humedad Minima:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(3f),
            )
            Text(
                text = "${OptionsDevice.valor_minimo}%",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

