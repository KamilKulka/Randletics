package com.kamilkulka.randletics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kamilkulka.randletics.ui.theme.RandleticsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandleticsTheme {
                // A surface container using the 'background' color from the theme
                RandleticsNavigation()
            }
        }
    }
}
