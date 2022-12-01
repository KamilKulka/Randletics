package com.kamilkulka.randletics.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.kamilkulka.randletics.ui.theme.LimeGreen
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
fun AlertPopUp(
    modifier: Modifier = Modifier,
    contentText: String,
    confirmButtonText: String = "Yes",
    onConfirmClick: () -> Unit,
    dismissButtonText: String = "No",
    onDismissClick: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        title = { Text(text = contentText, fontSize = 16.sp) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmClick()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = LimeGreen,
                    backgroundColor = Color.Transparent
                )
            ) {
                Text(text = confirmButtonText, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissClick()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = LimeGreen,
                    backgroundColor = Color.Transparent
                )
            ) {
                Text(text = dismissButtonText, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        }, backgroundColor = DustyGreen,
        contentColor = LimeGreen,
        shape = RoundedCornerShape(24.dp)
    )
}

@Composable
fun TopIconButton(imageVector: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.padding(24.dp)) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(38.dp)
        )
    }
}

@Composable
fun FilterDropDown(
    modifier: Modifier = Modifier,
    filterCategoryName: String,
    chosenElement: String,
    expanded: Boolean,
    onDropButtonClick: () -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = filterCategoryName,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Box {
            Row {
                Text(
                    text = chosenElement,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { onDropButtonClick() })
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismissRequest,
                content = content
            )
        }
    }
}