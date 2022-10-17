package com.kamilkulka.randletics.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.ui.theme.BlueGray
import com.kamilkulka.randletics.ui.theme.YellowFreesia

@Composable
fun MainScreen() {
    Scaffold(){contentPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(contentPadding),
            color = YellowFreesia
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    ,
                    painter = rememberImagePainter(R.drawable.main_photo
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                WorkoutsRow(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(6.dp))
            }
        }
    }

}

@Composable
fun WorkoutsRow(modifier: Modifier = Modifier) {
    LazyRow(modifier
        ){
        items(3){ WorkoutBox()}
    }
}

@Composable
fun WorkoutBox() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = 5.dp
    ) {

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}