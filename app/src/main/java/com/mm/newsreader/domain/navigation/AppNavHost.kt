package com.mm.newsreader.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mm.newsreader.domain.navigation.RouteConstants.DETAILS_SCREEN_ID
import com.mm.newsreader.presentation.detailsscreen.DetailsScreenRoute
import com.mm.newsreader.presentation.mainscreen.MainScreenRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Routes.MainScreen.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.MainScreen.route) {
            MainScreenRoute(
                onItemClick = {
                    navController.navigate("${Routes.DetailsScreen.route}/$it")
                }
            )
        }

        val detailsScreenRoute = "${Routes.DetailsScreen.route}/{${DETAILS_SCREEN_ID.name}}"
        composable(
            route = detailsScreenRoute, arguments = listOf(
                navArgument(
                    DETAILS_SCREEN_ID.name
                ) { type = NavType.StringType }
            )
        ) {
            DetailsScreenRoute(
                itemId = it.arguments?.getString(DETAILS_SCREEN_ID.name),
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
