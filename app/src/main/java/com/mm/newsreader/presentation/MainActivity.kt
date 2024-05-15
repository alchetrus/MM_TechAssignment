package com.mm.newsreader.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mm.newsreader.domain.navigation.AppNavHost
import com.mm.newsreader.presentation.ui.theme.NewsReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsReaderTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}