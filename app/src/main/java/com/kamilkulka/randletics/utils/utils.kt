package com.kamilkulka.randletics.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kamilkulka.randletics.screens.preworkout.PreWorkoutViewModel
import com.kamilkulka.randletics.ui.theme.Celadon
import com.kamilkulka.randletics.ui.theme.DustyGreen

@Composable
fun RowWithIcon(
    modifier: Modifier = Modifier.wrapContentSize(),
    imageVector: ImageVector,
    text: String = "",
    contentDescription: String = "",
    iconSize: Dp = 32.dp,
    spacerSize: Dp = 8.dp,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.Black
    ) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = color
        )
        Spacer(modifier = Modifier.size(spacerSize))
        Text(text = text, fontSize = fontSize, color = color)
    }
}

@Composable
fun PopUp(modifier: Modifier=Modifier,
          cornerRadius: Dp =24.dp,
          color: Color=Color.Transparent,
          description:String ="",
          descriptionBackground: Color = color,
          leftButtonText: String ="",
          leftButtonBackground: Color = color,
          leftButtonTextColor:Color = Color.Black,
          rightButtonText:String ="",
          rightButtonBackground: Color = color,
          rightButtonTextColor:Color = Color.Black,
          buttonBorderWidth: Dp = 1.dp,
          buttonBorderColor: Color = Color.Gray,
          onLeftButtonClick: ()->Unit={},
          onRightButtonClick: ()->Unit={}) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(cornerRadius),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(4f)
                        .background(color = descriptionBackground),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = description,
                        modifier = Modifier.padding(12.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)
                        .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
                        .background(color = Color.Transparent)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .border(width = buttonBorderWidth, color = buttonBorderColor, shape = RoundedCornerShape(bottomStart = cornerRadius))
                            .background(color = leftButtonBackground)
                            .clickable {
                                       onLeftButtonClick()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = leftButtonText,
                            color = leftButtonTextColor)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .border(width = buttonBorderWidth,color = buttonBorderColor, shape = RoundedCornerShape(bottomEnd = cornerRadius))
                            .background(color = rightButtonBackground)
                            .clickable {
                                       onRightButtonClick()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = rightButtonText,
                            color = rightButtonTextColor)
                    }
                }
            }
        }
    }
}