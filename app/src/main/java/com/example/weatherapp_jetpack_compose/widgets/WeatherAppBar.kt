package com.example.weatherapp_jetpack_compose.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title", icon: ImageVector? = null, isOnMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    TopAppBar(title = {
        Text(title, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))
    }, actions = {
        if (isOnMainScreen) {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "Search Icon")
            }
        }
    }, navigationIcon = {
        if(!isOnMainScreen){
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Search Icon")
            }
        }
    })
}