package com.kamilkulka.randletics.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
