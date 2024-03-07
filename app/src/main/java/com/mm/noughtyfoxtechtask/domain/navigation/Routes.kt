package com.mm.noughtyfoxtechtask.domain.navigation

import com.mm.noughtyfoxtechtask.domain.navigation.RouteConstants.DETAILS_SCREEN
import com.mm.noughtyfoxtechtask.domain.navigation.RouteConstants.MAIN_SCREEN

sealed class Routes(val route: String) {
    object MainScreen : Routes(MAIN_SCREEN.name)
    object DetailsScreen : Routes(DETAILS_SCREEN.name)
}

