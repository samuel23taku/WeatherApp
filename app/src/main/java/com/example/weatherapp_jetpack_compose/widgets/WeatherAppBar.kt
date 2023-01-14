package com.example.weatherapp_jetpack_compose.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp_jetpack_compose.model.Favorite
import com.example.weatherapp_jetpack_compose.navigation.WeatherScreens
import com.example.weatherapp_jetpack_compose.screens.viewmodels.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title", icon: ImageVector? = null, isOnMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onButtonClicked: () -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    val showToast = remember {
        mutableStateOf(false)
    }
    val toastMessage = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }
    if (showToast.value) {
        ShowToast(context = context, message = toastMessage.value)
        showToast.value = false
    }
    TopAppBar(title = {
        Text(title, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp))
    },

        actions = {
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
                    onButtonClicked.invoke()
                }, modifier = Modifier.clickable {

                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Search Icon")
                }
            }
            if (isOnMainScreen) {
                val favs = favoriteViewModel.favList.collectAsState().value
                val fav = Favorite(
                    city = title.split(",")[0],
                    country = title.split(",")[1]
                )
                IconButton(
                    onClick = {

                        if (favs.contains(fav)) {
                            toastMessage.value = "${fav.city} removed to Favorites"
                            favoriteViewModel.deleteFavorite(fav)
                            showToast.value = true
                        } else {
                            favoriteViewModel.insertFavorite(
                                fav
                            )
                            toastMessage.value = "${fav.city} added to Favorites"
                            showToast.value = true

                        }
                    }, modifier = Modifier
                        .scale(0.9f)
                ) {
                    Icon(
                        imageVector = if (favs.contains(fav)) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = Color.Red.copy(alpha = 0.6f)
                    )
                }
            }
        })
}

@Composable
fun ShowToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

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
                    onClick = {
                        expanded = false
                        navController.navigate(
                            when (text) {
                                "About" -> WeatherScreens.AboutScreen.name
                                "Favourites" -> WeatherScreens.FavoriteScreen.name
                                else -> WeatherScreens.SettingsScreen.name

                            }
                        )
                    },
                    text = {
                        Text(text, fontWeight = FontWeight.W300)
                    },
                    leadingIcon = {
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
