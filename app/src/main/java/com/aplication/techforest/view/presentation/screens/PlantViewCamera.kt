package com.aplication.techforest.view.presentation.screens

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aplication.techforest.ui.theme.BabyPink
import com.aplication.techforest.ui.theme.LightRed
import com.aplication.techforest.viewmodel.EditOptionsScreenViewModel
import com.aplication.techforest.viewmodel.PlantsViewModel
import com.bumptech.glide.Glide
import java.io.File


@Composable
fun PlantViewCamera(
    uri : String,
    navHostController: NavHostController,
    viewModel: PlantsViewModel = hiltViewModel()
){
    val uri = Uri.parse(uri)
    val images = File(uri.toString())

    imagePicker(uri = uri)
    showResults(viewModel = viewModel, images = images)
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ImagePicker(uri : Uri){
    var imageUrl by remember {mutableStateOf<Uri?>(uri)}
    val context = LocalContext.current
    val bitmap = remember{mutableStateOf<Bitmap?>(null)}

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){ uri : Uri? ->
        imageUrl = uri
    }

    Column() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(BabyPink),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tech Forest",
                color = Color.DarkGray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            imageUrl?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Gallery Image",
                        modifier = Modifier.size(400.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = {
                    launcher.launch("image/*")
                }
            ) {
                Text(
                    text = "Click Image",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

@ExperimentalCoilApi
@Composable
fun imagePicker(uri: Uri){
    Image(painter = rememberImagePainter(data = uri), contentDescription = null)
}

@Composable
fun showResults(
    viewModel: PlantsViewModel,
    images: File,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
){
    val context = LocalContext.current
    val plants = viewModel.getPlants(images)
    viewModel.myResponse.observe(lifecycleOwner, Observer { response ->
        if (response.isSuccessful) {
            Log.d("Options", response.body().toString())
            Log.d("Options", response.code().toString())
            Log.d("Options", response.message())
            viewModel.myResponse
        }
        else{
            Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
        }
    })
}