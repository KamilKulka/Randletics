package com.kamilkulka.randletics.screens.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.models.Workout
import com.kamilkulka.randletics.ui.theme.Ivory
import com.kamilkulka.randletics.ui.theme.Celadon
import com.kamilkulka.randletics.ui.theme.DustyRose
import com.kamilkulka.randletics.ui.theme.SageGreen

@Composable
fun MainScreen(
    onAddWorkout: () -> Unit,
    onWorkoutClick: (Workout) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val workoutList = viewModel.workoutsList.collectAsState().value
    Scaffold() { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            color = DustyRose
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
//                    items(workoutList.size) { index ->
//                        WorkoutBox(workout = workoutList[index])
                    items(3) {
                        WorkoutBox()
                    }
                }
            }
        }
    }

}


@Composable
fun WorkoutBox(
//    workout: Workout
) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(6.dp)
            .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
            .background(SageGreen)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val startColorPoint = Offset(0f,height*0.2f)
        val endColorPoint = Offset(width.toFloat(), height*0.6f)
        //ColoredPath
        val colorLayerPath = Path().apply{
            moveTo(startColorPoint.x,startColorPoint.y)
//            quadraticBezierTo(
//                startColorPoint.x,
//                startColorPoint.y,
//                abs(startColorPoint.x + endColorPoint.x)/2f,
//                abs(startColorPoint.y + endColorPoint.y)/2f
//            )
            lineTo(endColorPoint.x,endColorPoint.y)
            lineTo(width.toFloat()+100f,height.toFloat()+100f)
            lineTo(-100f,height.toFloat()+100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()){
            drawPath(path = colorLayerPath, color = Celadon)
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)){
            Text(text = "Title", modifier = Modifier.align(Alignment.TopStart))
        }
    }
}

@Composable
fun AddWorkoutBox(){
    Box(modifier = Modifier
        .aspectRatio(1f)
        .padding(6.dp)
        .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
        .background(SageGreen))
}

