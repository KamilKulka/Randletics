package com.kamilkulka.randletics.screens.newworkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.ui.theme.Celadon
import com.kamilkulka.randletics.ui.theme.DustyRose
import com.kamilkulka.randletics.ui.theme.SageGreen

@Composable
fun NewWorkoutScreen(
//    onAddWorkout: () -> Unit,
//    onDiscardButton: () -> Unit,
    navController: NavController,
    viewModel: NewWorkoutViewModel = hiltViewModel()
) {
    Scaffold(backgroundColor = DustyRose,
        topBar = {
        TopAppBar(
            elevation = 0.dp,
            contentPadding = PaddingValues(12.dp),
            backgroundColor = Color.Transparent
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navController.popBackStack()
                    })
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = "Add Workout")
        }
    }) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            item {
                FillInContentBox(title = "test") {
                    BasicTextField(textStyle = TextStyle(fontSize = 20.sp),
                        value = "aa",
                        onValueChange = {})
                }
            }
            item {
                FillInContentBox(title = "Difficulty") {
                    Slider(value = viewModel.difficultySliderState,
                        onValueChange = {   value ->
                        viewModel.difficultySliderState = value
                    },steps = 1, colors = SliderDefaults.colors(
                            thumbColor = SageGreen,
                            disabledThumbColor = SageGreen
                    ))
                }
            }
        }
    }
}


@Composable
fun FillInContentBox(title: String = "", content:@Composable ()-> Unit={}){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
        .background(Celadon),
        contentAlignment = Alignment.TopStart
    ) {
        Column(modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(12.dp)) {
            Text(text = title, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            content()
        }

    }
}

@Composable
@Preview(showBackground = true)
fun FillInContentBoxPreview(){
    FillInContentBox(title= "Title:")
}

@Composable
//@Preview(showBackground = true)
fun NewWorkoutScreenPreview() {
    Scaffold(topBar = {
        TopAppBar(
            elevation = 0.dp,
            contentPadding = PaddingValues(12.dp),
            backgroundColor = Color.Transparent
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
//                        navController.popBackStack()
                    })

        }
    }) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

        }
    }
}