package com.example.deportestr.ui.screens.tenis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R
import com.example.deportestr.ui.models.Athlete
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.User
import com.example.deportestr.ui.screens.basket.ItemBasket
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
/**
 * funcion que carga el drawer y la interfaz
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TenisScreen(
    goLogin: () -> Unit,
    goHome: (String) -> Unit,
    email: String,
    viewModel: TenisViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val user = viewModel.user
    val athletes = viewModel.athletes
    val events = viewModel.events

    //Este launched effect al cargar la pantalla cambia unit y hace la busqueda del usuario para el drawer
    //Y carga la informacion de los equipos y de los eventos/partidos
    LaunchedEffect(Unit) {
        viewModel.loadInfo(email)
        while (user == null || athletes == null || events == null) {
            delay(200)
        }
    }
    //Un drawer el cual deslizas en la pantalla y sale un desplegable de la izquierda con la informacion del usuario

    if (user != null) {
        ModalNavigationDrawer(drawerContent = {
            ModalDrawerSheet {
                DrawerContentTenis(user, goLogin = goLogin, goHome = goHome) {
                    coroutineScope.launch { drawerState.close() }
                }
            }
        }, drawerState = drawerState) {
            Scaffold(topBar = {
                TopBarTenis(onClickDrawer = { coroutineScope.launch { drawerState.open() } })
            }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(Color(0xFF303030))
                ) {
                    TenisContent(athletes, events)
                }

            }

        }
    }
}

//El contenido de la pantalla con los resultados se crean 1 item por cada evento deportivo
@Composable
fun TenisContent(athletes: List<Athlete>?, events: List<SportEvent>?) {
    if (events != null) {
        LazyVerticalGrid(columns = GridCells.Fixed(1), content = {
            items(events) { sportEvent ->
                ItemTenis(sportEvent = sportEvent, athlete = athletes, events)
            }
        })
    }
}

@Composable
fun ItemTenis(sportEvent: SportEvent, athlete: List<Athlete>?, events: List<SportEvent>) {
    val timestamp = sportEvent.date
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = dateFormat.format(timestamp)
    //Al no tener api externa hay que añadirle al evento los equipos que participen practicamente a mano
    //Todos los eventos y los deportes vienen de la base de datos
    when (sportEvent.id) {
        41 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[0].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[3].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        42 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[4].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[6].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        43 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[0].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[3].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        44 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[6].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[11].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        45 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[14].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[16].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        46 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[2].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[8].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        47 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[1].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[4].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        48 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (athlete != null) {
                    Text(
                        text = athlete[2].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                    Text(
                        text = athlete[17].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//El contenido del drawer que contiene los datos del usuario y navega hacia atras en este caso a la pantalla de home
@Composable
fun DrawerContentTenis(
    user: User?,
    goLogin: () -> Unit,
    goHome: (String) -> Unit,
    onCloseDrawer: () -> Unit
) {
    if (user != null) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable { onCloseDrawer() }
                        .size(100.dp)
                )
                Column {
                    Text(text = user.name!!)
                    Text(text = user.email!!)
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(), color = Color(0xFF757575)
            )
            Row(modifier = Modifier
                .clickable { goHome(user.email!!) }
                .fillMaxWidth()
            ) {
                Text(text = "Deportes", fontSize = 25.sp)
                Icon(
                    imageVector = Icons.Filled.NavigateNext,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(35.dp)
                )
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(), color = Color(0xFF757575)
            )
            Row(modifier = Modifier
                .clickable { goLogin() }
                .fillMaxWidth()

            ) {
                Text(text = "Cerrar session", color = Color(0xFFC70606), fontSize = 25.sp)
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = null,
                    tint = Color(0xFFC70606),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(35.dp)
                )
            }
        }
    }
}

/**
 * Barra superior de la pantalla
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTenis(onClickDrawer: () -> Unit) {
    TopAppBar(

        title = {
            Image(
                painter = painterResource(id = R.drawable.logo_bueno),
                contentDescription = null,
                modifier = Modifier
                    .size(175.dp)
            )
        },
        actions = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Red),
    )
}

