package com.kamilkulka.randletics.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.ui.theme.YellowFreesia

@Composable
fun MainScreen() {
    Scaffold() { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            color = YellowFreesia
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    painter = rememberImagePainter(
                        R.drawable.main_photo
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 5.dp,
                        end = 5.dp,
                        top = 5.dp,
                        bottom = 100.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(3) { WorkoutBox() }
                }
            }
        }
    }

}


@Composable
fun WorkoutBox(workout: Workout) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
            .padding(4.dp)
            .background()
    ) {

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}