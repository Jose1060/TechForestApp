package com.aplication.techforest.view.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.model.User.UserResponse
import com.aplication.techforest.utils.Resource
import com.aplication.techforest.view.presentation.components.AppBar
import com.aplication.techforest.view.presentation.components.ProfileHeader
import com.aplication.techforest.viewmodel.ProfileViewModel

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun Profiles(
    userId: Int,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val userInfo = produceState<Resource<UserResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getUserProfile(userId = userId)
    }.value

    Column {
        AppBar()
    }
    UserInfoStateWrapperProfile(userInfo = userInfo)
}


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun UserInfoStateWrapperProfile(
    userInfo: Resource<UserResponse>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
) {
    when (userInfo) {
        is Resource.Success -> {
            ProfileHeader(user = userInfo.data!!)
        }
        is Resource.Error -> {
            Text(
                text = userInfo.message!!,
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