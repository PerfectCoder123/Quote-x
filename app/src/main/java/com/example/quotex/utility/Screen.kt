package com.example.quotex.utility

sealed class Screen (val route : String){
    object Home : Screen("home")
    object Splash : Screen("splash")
}