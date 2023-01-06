package com.example.weatherapp_jetpack_compose.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp_jetpack_compose.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title", icon: ImageVector? = null, isOnMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }
    TopAppBar(title = {
        Text(title, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))
    }, actions = {
        if (isOnMainScreen) {
            IconButton(onClick = {
                onAddActionClicked.invoke()
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More Icon")
            }
        }
    }, navigationIcon = {
        if (icon != null) {
            IconButton(onClick = {
                navController.popBackStack()
            }, modifier = Modifier.clickable {

            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Search Icon")
            }
        }
    })
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val moreItems = listOf("About", "Favourites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
            showDialog.value = false
        }, modifier = Modifier.width(140.dp)) {
            moreItems.forEachIndexed { index, text ->
                DropdownMenuItem(
                    onClick={
                        expanded = false
//                        showDialog.value = false
                        navController.navigate(when(text){
                      "About" -> WeatherScreens.AboutScreen.name
                      "Favourites" -> WeatherScreens.FavoriteScreen.name
                              else -> WeatherScreens.SettingsScreen.name

                        })
                    },
                    text = {
                           Text(text,fontWeight = FontWeight.W300)
                    }, leadingIcon = {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Default.Info
                                "Favourites" -> Icons.Default.FavoriteBorder
                                else -> Icons.Default.Settings
                            }, contentDescription = "$text Icon"
                        )
                    },
                    )
            }
        }

    }
}
