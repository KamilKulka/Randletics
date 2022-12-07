package com.kamilkulka.randletics.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kamilkulka.randletics.R
import com.kamilkulka.randletics.RandleticsScreens
import com.kamilkulka.randletics.ui.theme.Beige
import com.kamilkulka.randletics.ui.theme.DustyGreen
import com.kamilkulka.randletics.utils.AlertPopUp

@Composable
fun WorkoutScreen(
    navController: NavController,
    viewModel: WorkoutViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val mainTxtColor= MaterialTheme.colors.onBackground

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.primary
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
                        text = viewModel.getExercisesLeft().toString(),
                        color = DustyGreen,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 42.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.label_exercises) + " \n" + stringResource(
                            id = R.string.text_left
                        ),
                        color = Beige,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(4.dp)
                    )
                }
                Button(
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.surface,
                        contentColor = MaterialTheme.colors.onSurface
                    ),
                    contentPadding = PaddingValues(
                        top = 10.dp,
                        bottom = 10.dp,
                        start = 14.dp,
                        end = 14.dp
                    ),
                    onClick = { viewModel.setExitPopup() }) {
                    Icon(
                        imageVector = Icons.Rounded.Flag,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryVariant)
                    Text(
                        text = stringResource(id = R.string.label_finish_workout),
                        fontSize = 16.sp
                    )
                }
            }

            if (viewModel.restScreen.collectAsState().value) {
                Divider(
                    modifier = Modifier.padding(24.dp),
                    color = MaterialTheme.colors.surface
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(modifier = Modifier.wrapContentHeight()) {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 42.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colors.onSecondary
                                    )
                                ) {
                                    append(stringResource(id = R.string.rest_1) + "\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 42.sp,
                                        color = MaterialTheme.colors.surface,
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(stringResource(id = R.string.rest_2))
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
                            Text(
                                text = viewModel.timeLeft.collectAsState().value.toString(),
                                fontSize = 42.sp,
                                color = MaterialTheme.colors.onSecondary
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(
                                text = stringResource(id = R.string.label_seconds_left),
                                fontSize = 16.sp,
                                color = MaterialTheme.colors.onSecondary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(end = 50.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            modifier = Modifier.width(100.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = MaterialTheme.colors.onSecondary
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
                            Text(text = stringResource(id = R.string.label_skip), fontSize = 16.sp)
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.75f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = viewModel.getExerciseTitle(),
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Medium,
                            color = mainTxtColor
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            Button(
                                modifier = Modifier.wrapContentSize(),
                                shape = CircleShape,
                                contentPadding = PaddingValues(
                                    vertical = 10.dp,
                                    horizontal = 14.dp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.secondary,
                                    contentColor = MaterialTheme.colors.onSecondary
                                ),
                                onClick = {
                                    viewModel.openTutorial(context)
                                }) {
                                Text(
                                    text = stringResource(id = R.string.label_tutorial),
                                    fontSize = 16.sp
                                )
                                Icon(
                                    imageVector = Icons.Rounded.PlayArrow,
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = viewModel.getReps().toString(),
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = mainTxtColor
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = stringResource(id = R.string.label_repetitions_short),
                                    fontSize = 16.sp,
                                    color = mainTxtColor,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(60.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontSize = 32.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    ) {
                                        append(viewModel.getSeries().toString())
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 18.sp
                                        )
                                    ) {
                                        append("/3")
                                    }
                                })
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = stringResource(id = R.string.label_series),
                                    fontSize = 16.sp,
                                    color = mainTxtColor,
                                    textAlign = TextAlign.Center
                                )

                            }

                        }
                        Spacer(modifier = Modifier.size(24.dp))
                        Text(text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = mainTxtColor
                                )
                            ) {
                                append(stringResource(id = R.string.label_equipment) + ":\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = mainTxtColor,
                                    fontSize = 16.sp)
                            ) {
                                append(
                                    viewModel.getEquipmentForExercise()
                                        ?: stringResource(id = R.string.label_no_equipment)
                                )
                            }

                        }, modifier = Modifier.fillMaxWidth())
                    }
                    Text(text = buildAnnotatedString {
                        val txtNext = stringResource(id = R.string.label_next)
                        val txtEndOfWorkout = stringResource(id = R.string.label_end_of_workout)

                        withStyle(style = ParagraphStyle(lineHeight = 32.sp)) {
                            withStyle(
                                style = SpanStyle(
                                    color = mainTxtColor,
                                    fontSize = 16.sp)
                            ) {
                                append(txtNext + "\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = mainTxtColor
                                )
                            ) {
                                append(
                                    viewModel.getNextExerciseTitle() ?: txtEndOfWorkout
                                )
                            }
                        }
                    }, modifier = Modifier.fillMaxWidth(0.75f))
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background), contentAlignment = Alignment.Center
                ) {
                    Button(
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondary,
                            contentColor = MaterialTheme.colors.onSecondary
                        ),
                        contentPadding = PaddingValues(
                            vertical = 10.dp,
                            horizontal = 18.dp
                        ),
                        onClick = {
                            if (viewModel.getExercisesLeft() == 0 && viewModel.getSeries() == 3) {
                                navController.popBackStack(
                                    route = RandleticsScreens.MainScreen.name,
                                    inclusive = false
                                )
                            } else {
                                viewModel.complete()
                            }
                        }) {
                        val buttonText =
                            if (viewModel.getExercisesLeft() == 0 && viewModel.getSeries() == 3) stringResource(
                                id = R.string.label_complete
                            ) else stringResource(id = R.string.label_next)
                        Text(text = buttonText, fontSize = 24.sp)
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowRight,
                            contentDescription = stringResource(id = R.string.label_complete)
                        )
                    }
                }
            }

        }
        if (viewModel.exitPopup.collectAsState().value) {
            AlertPopUp(
                contentText = stringResource(id = R.string.finish_workout_inquiry),
                onConfirmClick = {
                    viewModel.setExitPopup()
                    navController.popBackStack(
                        route = RandleticsScreens.MainScreen.name,
                        inclusive = false
                    )
                },
                onDismissClick = {
                    viewModel.setExitPopup()
                },
                onDismissRequest = {
                    viewModel.setExitPopup()
                })
        }


    }

}


