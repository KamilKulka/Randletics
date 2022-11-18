package com.kamilkulka.randletics.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Flag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
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
                    colors = ButtonDefaults.buttonColors(
                    backgroundColor = DimmedSageGreen,
                    contentColor = DustyGreen),
                    contentPadding = PaddingValues(10.dp),
                    onClick = { /*TODO Finish Workout*/ }) {
                    Icon(imageVector = Icons.Rounded.Flag, contentDescription = "End")
                    Text(text = "Finish Workout", fontSize = 16.sp)
                }
            }
            Column(modifier = Modifier
                .background(DustyGreen)
                .fillMaxHeight()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                        .aspectRatio(ratio = 1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.isVisibleProgressBar.collectAsState().value) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            progress = viewModel.progressBar.collectAsState().value,
                            color = DustyGreen,
                            strokeWidth = 12.dp
                        )
                        Text(
                            text = viewModel.timeLeft.collectAsState().value.toString(),
                            fontSize = 48.sp
                        )

                    } else {
                        OutlinedButton(onClick = {
                            viewModel.changeProgressBarVisibility()
                            viewModel.startCounter()
                        }) {

                        }
                    }
                }

            }
        }
    }
}