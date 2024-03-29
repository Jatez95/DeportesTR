package com.example.deportestr.ui.screens.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R

/**
 * Esta funcion es la que se llama cuando navegas desde el login
 * la cual carga la interfaz
 */
@Composable
fun ForgotScreen(
    goLogin: () -> Unit,
    viewModel: ForgotViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Forgotted(Modifier.align(Alignment.Center), viewModel, goLogin)
    }
}

/**
 * Una vez llegado aqui se carga en forma de columna
 * Esta hecho asi para un mejor entendimiento y limpieza
 */
@Composable
fun Forgotted(modifier: Modifier, viewModel: ForgotViewModel, goLogin: () -> Unit) {
    val email = viewModel.email
    val password = viewModel.newPassword
    val repeatPassword = viewModel.reapeatPassword
    val loginEnabled = viewModel.loginEnabled
    viewModel.onRegistrationChanged(email, password, repeatPassword)

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(modifier = Modifier) {
        HeaderImageChange()
        SignChange(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(15.dp))
        EmailChanged(email, viewModel)
        Spacer(modifier = Modifier.padding(15.dp))
        PasswordChanged(password, viewModel)
        Text(
            text = "*La contraseña debe de ser de mas de 6 caracteres",
            color = Color.Red
            )
        Spacer(modifier = Modifier.padding(15.dp))
        RepeateChanged(repeatPassword, viewModel)
        Spacer(modifier = Modifier.padding(16.dp))
        ChangePassword(loginEnabled, goLogin, viewModel)
    }
}
//El header con la imagen
@Composable
fun HeaderImageChange() {
    Image(
        painter = painterResource(id = R.drawable.logo_bueno),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}
//El titulo de la aplicacion
@Composable
fun SignChange(modifier: Modifier) {
    Text(
        text = "DeportesTR",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB10404),
        modifier = modifier
    )
}


//El textfield con el email
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailChanged(user: String, viewModel: ForgotViewModel) {
    TextField(
        value = user,
        onValueChange = { viewModel.email = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = stringResource(id = R.string.email_insert)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = stringResource(id = R.string.email_insert)) }
    )
}
//El textfield con para cambiar la contraseña
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordChanged(user: String, viewModel: ForgotViewModel) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = user,
        onValueChange = { viewModel.newPassword = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = stringResource(id = R.string.password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "show")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        label = { Text(text = stringResource(id = R.string.password_insert)) }
    )
}
//Repetir la contraseña en caso de que no sean iguales no se activa el boton de iniciar sesion
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeateChanged(user: String, viewModel: ForgotViewModel) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = user,
        onValueChange = { viewModel.reapeatPassword = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = stringResource(id = R.string.password_repeat)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        /*
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFFFFFFF),
            containerColor = Color(0xFF1D1D1D),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),

         */
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "show")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        label = { Text(text = stringResource(id = R.string.password_insert)) }
    )
}
//Boton de inicio de sesion este lleva a la pantalla de inicio la cual te permite buscar el usuario
//con tu nueva contraseña
@Composable
fun ChangePassword(loginEnabled: Boolean, goLogin: () -> Unit, viewModel: ForgotViewModel) {
    Button(
        onClick = {
            //Una vez introducidos los datos al pinchar en el boton buscara al usuario por email
            //y despues enviara al usuario buscado por email con la nueva contraseña y se cambiara en la api rest
            //aparte te navegara al inicio de sesion para iniciar sesion
            viewModel.resetPassword()
            goLogin()
        },
        modifier = Modifier
            .padding(5.dp)
            .width(IntrinsicSize.Max)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xFFFFFFFF),
            disabledContentColor = Color(0xFF882D2D),
            containerColor = Color(0xFF882D2D)
        ), enabled = loginEnabled
    ) {
        Text(text = stringResource(id = R.string.sign_in))
    }
}