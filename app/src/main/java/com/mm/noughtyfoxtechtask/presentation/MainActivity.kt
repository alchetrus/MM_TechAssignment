package com.mm.noughtyfoxtechtask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.mm.noughtyfoxtechtask.domain.navigation.AppNavHost
import com.mm.noughtyfoxtechtask.presentation.ui.theme.NoughtyFoxTechTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoughtyFoxTechTaskTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}