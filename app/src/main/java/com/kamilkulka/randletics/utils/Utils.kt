package com.kamilkulka.randletics.utils

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.kamilkulka.randletics.ui.theme.DimmedSageGreen
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
                    contentColor = DimmedSageGreen,
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
                    contentColor = DimmedSageGreen,
                    backgroundColor = Color.Transparent
                )
            ) {
                Text(text = dismissButtonText, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        }, backgroundColor = DustyGreen,
        contentColor = DimmedSageGreen,
        shape = RoundedCornerShape(24.dp)
    )
}

@Composable
fun WebViewBox(modifier: Modifier = Modifier, url: String) {
    AndroidView(modifier = modifier, factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }

    },
        update = {
            it.loadUrl(url)
        })
}