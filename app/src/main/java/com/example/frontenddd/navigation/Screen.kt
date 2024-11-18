package com.example.frontenddd.navigation

sealed class Screen (val route: String){
    object DetailResep : Screen("detail_resep")
    object About : Screen("about")
    object HomeScreen : Screen("HomeScreen")
    object Grid : Screen("grid")

}