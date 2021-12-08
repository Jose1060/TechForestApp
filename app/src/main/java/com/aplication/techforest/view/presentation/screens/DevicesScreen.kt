package com.aplication.techforest.view.presentation.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.R
import com.aplication.techforest.model.Device.DeviceResponse
import com.aplication.techforest.navigation.Destinations
import com.aplication.techforest.ui.theme.*
import com.aplication.techforest.utils.Resource
import com.aplication.techforest.viewmodel.DeviceViewModel


@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Devices(
    viewModel: DeviceViewModel = hiltViewModel(),
    userId: Int,
    navController: NavController,
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val deviceData = produceState<Resource<DeviceResponse>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getDevicesData(userId = userId)
    }.value

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Box(
                modifier = Modifier
                    .background(DarkStateGray)
                    .fillMaxSize()
            ) {
                Column {
                    Title()
                    DeviceDetailStateWrapper(deviceInfo = deviceData, navController)
                }
            }
        }
    }
}


@Composable
fun DeviceListItem(
    color: Color = BlueViolet3,
    device: DeviceResponse,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = device.nombre,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = device.id.toString(),
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
            Text(
                text = device.estado.toString(),
                style = MaterialTheme.typography.body2,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier
                    .size(16.dp)
                    .clickable {
                        Log.d("DeviceID_DS", "${device.id}")
                        navController.navigate(Destinations.DeviceDetail.createRoute(deviceId = device.id))
                    }
            )
        }
    }
}


@Composable
fun Title(
    name: String = "Opciones"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
fun DeviceListItem2(device: DeviceResponse) {

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = device.id.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Title: ${device.nombre}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = "Status: ${device.estado}",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
fun DeviceDetailStateWrapper(
    deviceInfo: Resource<DeviceResponse>,
    navController: NavController,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (deviceInfo) {
        is Resource.Success -> {
            DeviceListItem(
                device = deviceInfo.data!!,
                navController = navController
            )
        }
        is Resource.Error -> {
            Text(
                text = deviceInfo.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            Box() {
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

