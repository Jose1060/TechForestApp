package com.aplication.techforest.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.ui.theme.BabyPink
import com.aplication.techforest.utils.Resource
import com.aplication.techforest.viewmodel.DeviceDetailViewModel

@Composable
fun DeviceDetailScreen(
    viewModel: DeviceDetailViewModel = hiltViewModel(),
    deviceId: Int
) {
    val deviceData = produceState<Resource<DeviceResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getDeviceData(deviceId)
    }.value

}


@Composable
fun DeviceDataStateWrapper(
    deviceData: Resource<DeviceResponse>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
) {
    when (deviceData) {
        is Resource.Success -> {

        }
        is Resource.Error -> {

        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = BabyPink,
                modifier = loadingModifier,
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Preview
@Composable
fun DeviceDataList(
    //deviceData : Resource<DeviceResponse>,
) {
    var expandedState by remember{
        mutableStateOf(false)}
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Card(
                elevation = 15.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            ) {
                Image(
                    painter = rememberImagePainter(data = "https://images.pexels.com/photos/9604597/pexels-photo-9604597.jpeg"),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nombre del dispositivo:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(3f),
                )
                Text(
                    text = "A005",
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
                    text = "Activo",
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
                    text = "04-12-21",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

