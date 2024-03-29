package com.example.deportestr.ui.screens.registration

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
 * Funcion de registro por donde entra la navegacion y carga la interfaz
 */
@Composable
fun RegisterScreen(
    goLogin: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Register(Modifier.align(Alignment.Center), viewModel, goLogin)
    }
}

/**
 * Funcion hecha en forma de columna llamando a pequeñas funciones para un mejor entendimiento del codigo
 */
@Composable
fun Register(modifier: Modifier, viewModel: RegistrationViewModel, goLogin: () -> Unit) {
    val name = viewModel.name
    val email = viewModel.email
    val password = viewModel.password
    val repeatPassword = viewModel.reapeatPassword
    val loginEnabled = viewModel.loginEnabled
    viewModel.OnRegistrationChanged(email, password, repeatPassword)

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(modifier = Modifier) {
        HeaderImageRegistration()
        SignTitle(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(15.dp))
        EmailRegister(email, viewModel)
        Spacer(modifier = Modifier.padding(15.dp))
        NameRegister(name, viewModel)
        Spacer(modifier = Modifier.padding(15.dp))
        PasswordRegister(password, viewModel)
        Text(
            text = "*La contraseña debe de ser de mas de 6 caracteres",
            color = Color.Red
        )
        Spacer(modifier = Modifier.padding(15.dp))
        RepeatePassword(repeatPassword, viewModel)
        Spacer(modifier = Modifier.padding(16.dp))
        LogButton(loginEnabled, goLogin, viewModel)
    }
}
//Imagen del header o de la cabeza
@Composable
fun HeaderImageRegistration() {
    Image(
        painter = painterResource(id = R.drawable.logo_bueno),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}
//Titulo de la aplicacion
@Composable
fun SignTitle(modifier: Modifier) {
    Text(
        text = "DeportesTR",
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFB10404),
        modifier = modifier
    )
}


//El textfield del email para registrar el email del usuario
//En caso de que no sea un email valido no se activara el boton de iniciar session
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailRegister(user: String, viewModel: RegistrationViewModel) {
    TextField(
        value = user,
        onValueChange = { viewModel.email = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = stringResource(id = R.string.email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        label = { Text(text = "Introduce tu correo") }
    )
}
//El textfield del nombre para registrar el nombre del usuario
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameRegister(name: String, viewModel: RegistrationViewModel) {
    TextField(
        value = name,
        onValueChange = { viewModel.name = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
        placeholder = { Text(text = stringResource(id = R.string.name)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
        label = { Text(text = stringResource(id = R.string.name_insert)) }
    )
}
//EL Textfield con la contraseña del usuario en caso de que la contraseña no tenga mas de seis caracteres
//no se activara el boton de iniciar sesion
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRegister(user: String, viewModel: RegistrationViewModel) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = user,
        onValueChange = { viewModel.password = it },
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

//El Textfield de repetir la contraseña es igual que el de contraseña
//Pero si la contraseña de este textfield no es igual al anterior el boton se quedara desactivado
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatePassword(user: String, viewModel: RegistrationViewModel) {
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
//Boton el cuan guarda al usuario en la base de datos y navega a la pantalla de inicio de sesion
//para validar el usuario
@Composable
fun LogButton(loginEnabled: Boolean, goLogin: () -> Unit, viewModel: RegistrationViewModel) {
    Button(
        onClick = {
            viewModel.addUser()
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