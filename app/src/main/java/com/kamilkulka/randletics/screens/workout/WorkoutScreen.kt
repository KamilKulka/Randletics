package com.kamilkulka.randletics.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.ui.theme.Celadon
import com.kamilkulka.randletics.ui.theme.DimmedSageGreen
import com.kamilkulka.randletics.ui.theme.DustyGreen
import com.kamilkulka.randletics.ui.theme.SageGreen

@Composable
fun WorkoutScreen(
    navController: NavController,
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = SageGreen
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "5",
                        color = DustyGreen,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 42.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "Exercises \nleft",
                        color = Celadon,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Button(
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = DimmedSageGreen,
                        contentColor = DustyGreen
                    ),
                    contentPadding = PaddingValues(
                        top = 10.dp,
                        bottom = 10.dp,
                        start = 14.dp,
                        end = 14.dp
                    ),
                    onClick = { /*TODO Finish Workout*/ }) {
                    Icon(imageVector = Icons.Rounded.Flag, contentDescription = "End")
                    Text(text = "Finish Workout", fontSize = 16.sp)
                }
            }

            if (viewModel.restScreen.collectAsState().value) {
                Divider(
                    modifier = Modifier.padding(24.dp),
                    color = DimmedSageGreen)
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(modifier = Modifier.wrapContentHeight()) {
                        Text(
                            buildAnnotatedString{
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 42.sp,
                                        fontWeight = FontWeight.SemiBold)){
                                    append("Relax,\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 42.sp,
                                        color = Celadon,
                                        fontWeight = FontWeight.Light)){
                                    append("breathe out")
                                }
                        })
                    }
                    Box(
                        modifier = Modifier
                            .size(280.dp)
                            .aspectRatio(ratio = 1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            progress = viewModel.progressBar.collectAsState().value,
                            color = DustyGreen,
                            strokeWidth = 12.dp
                        )
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = viewModel.timeLeft.collectAsState().value.toString(),
                            fontSize = 42.sp)
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(text = "Seconds Left",
                                fontSize = 16.sp,
                                color = Celadon,
                                textAlign = TextAlign.Center)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(end=50.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            modifier = Modifier.width(100.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = DimmedSageGreen,
                                contentColor = DustyGreen
                            ),
                            contentPadding = PaddingValues(
                                top = 10.dp,
                                bottom = 10.dp,
                                start = 14.dp,
                                end = 14.dp
                            ),
                            onClick = {
                                viewModel.skipCounter()
                            }) {
                            Text(text = "Skip", fontSize = 16.sp)
                            Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "Skip")
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .background(DustyGreen)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = DimmedSageGreen,
                            contentColor = DustyGreen
                        ),
                        contentPadding = PaddingValues(
                            top = 10.dp,
                            bottom = 10.dp,
                            start = 14.dp,
                            end = 14.dp
                        ),
                        onClick = {
                            viewModel.changeRestScreen()
                            viewModel.startCounter()
                        }) {
                        Text(text = "Complete", fontSize = 24.sp)
                        Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "Complete")
                    }
                }
            }
        }
    }
}