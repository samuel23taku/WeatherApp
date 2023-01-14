package com.example.weatherapp_jetpack_compose.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp_jetpack_compose.R
import com.example.weatherapp_jetpack_compose.model.WeatherItem
import com.example.weatherapp_jetpack_compose.utils.formatDate
import com.example.weatherapp_jetpack_compose.utils.formatDateTime


@Composable
fun WeatherDetailRow(weatherItem: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Surface {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(formatDate(weatherItem.dt).split(",")[0])
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Weather Image", modifier = Modifier.size(60.dp)
                )
                WeatherStatus(weatherItem.weather[0].main)
                HighLowWeather(
                    high = weatherItem.temp.max.toInt(),
                    low = weatherItem.temp.min.toInt()
                )
            }
        }
    }


}

@Composable
fun WeatherStatus(weatherStatus: String) {
    Surface(
        color = Color(0xFFFFC400),
        shape = RoundedCornerShape(5.dp),

        ) {
        Text(
            weatherStatus,
            color = Color.Black,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Composable
fun HighLowWeather(high: Int, low: Int) {
    Row {
        Text("$high°", fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(Modifier.width(8.dp))
        Text("$low°", fontWeight = FontWeight.SemiBold, color = Color.LightGray)
    }
}


@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "Weather Image", modifier = Modifier.size(80.dp)
    )
}


@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HumidityWindPressureItem(
            itemDrawable = R.drawable.humidity,
            text = "${weather.humidity}",
            contentDescription = "Humidity Item"
        )
        HumidityWindPressureItem(
            itemDrawable = R.drawable.pressure,
            text = "${weather.pressure} psi",
            contentDescription = "Humidity Item"
        )
        HumidityWindPressureItem(
            itemDrawable = R.drawable.wind,
            text = "${weather.humidity} mph",
            contentDescription = "Wind icon"
        )
    }

}

@Composable
fun HumidityWindPressureItem(itemDrawable: Int, text: String, contentDescription: String) {
    Row(modifier = Modifier.padding(4.dp)) {
        Icon(
            painter = painterResource(id = itemDrawable),
            contentDescription = contentDescription,
            modifier = Modifier.size(20.dp)
        )
        Text(text, style = MaterialTheme.typography.caption)
    }
}

@Composable
fun SunsetSunriseRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(3.dp)
            )
            Text(text = formatDateTime(weather.sunrise), style = MaterialTheme.typography.caption)
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier
                    .size(20.dp)
                    .padding(3.dp)
            )
            Text(text = formatDateTime(weather.sunset), style = MaterialTheme.typography.caption)
        }
    }
}
