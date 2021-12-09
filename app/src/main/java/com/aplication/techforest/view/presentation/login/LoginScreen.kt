package com.aplication.techforest.view.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import com.aplication.techforest.model.LoginState
import com.aplication.techforest.view.presentation.components.EventDialog
import com.aplication.techforest.view.presentation.components.RoundedButton
import com.aplication.techforest.view.presentation.components.TransparentTextField


@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable

fun LoginScreen(
    state: LoginState,
    onLogin: (String, String) -> Unit,

    onDismissDialog: () -> Unit
) {

    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = com.aplication.techforest.R.drawable.login_background3),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConstraintLayout {
                val (surface, fab) = createRefs()
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = "Bienvenido a TechForest !",
                            style = MaterialTheme.typography.h5.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Text(
                            text = "Login ",
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TransparentTextField(
                                textFieldValue = emailValue,
                                textLabel = "Email",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next
                            )

                            TransparentTextField(
                                textFieldValue = passwordValue,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()

                                        onLogin(emailValue.value, passwordValue.value)
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (passwordVisibility) {
                                                Icons.Default.Visibility
                                            } else {
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Toggle Password Icon"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                            )

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Olvidaste tu Contraseña?",
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.End
                            )
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            RoundedButton(
                                text = "Login",
                                displayProgressBar = state.displayProgressBar,
                                onClick = {
                                    onLogin(emailValue.value, passwordValue.value)
                                }
                            )
                            ClickableText(
                                text = buildAnnotatedString {
                                    append("No tienes una cuenta?")
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append(" Registrate")
                                    }
                                }
                            ) {
                                // TODO("NAVIGATE TO REGISTER SCREEN")
                            }
                        }
                    }
                }

                FloatingActionButton(
                    modifier = Modifier
                        .size(72.dp)
                        .constrainAs(fab) {
                            top.linkTo(surface.top, margin = (-36).dp)
                            end.linkTo(surface.end, margin = 36.dp)
                        },
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Forward Icon",
                        tint = Color.White
                    )
                }
            }
        }

        if (state.errorMessage != null) {
            EventDialog(
                errorMessage = state.errorMessage as Int,
                onDismiss = onDismissDialog
            )
        }
    }
}