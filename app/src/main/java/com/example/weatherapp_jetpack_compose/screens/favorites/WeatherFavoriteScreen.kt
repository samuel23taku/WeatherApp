package com.example.weatherapp_jetpack_compose.screens.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp_jetpack_compose.model.Favorite
import com.example.weatherapp_jetpack_compose.navigation.WeatherScreens
import com.example.weatherapp_jetpack_compose.screens.viewmodels.FavoriteViewModel
import com.example.weatherapp_jetpack_compose.widgets.WeatherAppBar


@Composable
fun WeatherFavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favList = favoriteViewModel.favList.collectAsState().value
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Favorite Cities",
                isOnMainScreen = false,
                icon = Icons.Default.ArrowBack,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    modifier = Modifier.padding(2.dp),
                    contentPadding = PaddingValues(1.dp)
                ) {
                    this.items(items = favList) { favorite: Favorite ->
                        CityRow(
                            favorite = favorite,
                            navController = navController,
                            favoriteViewModel = favoriteViewModel
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CityRow(
    favorite: Favorite,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp).clickable {
                                     navController.navigate(WeatherScreens.MainScreen.name+"/${favorite.city}"){
                                         popUpTo(0)
                                     }
            },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(start = 4.dp))
            Surface {
                Text(text = favorite.country, modifier = Modifier.padding(4.dp))
            }
            IconButton(onClick = {
                favoriteViewModel.deleteFavorite(favorite)
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Icon")
            }
        }
    }
}
