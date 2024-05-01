package com.example.quotex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quotex.R
import com.example.quotex.utility.Screen


@Composable
fun SplashScreen(navController: NavController) {
    val customColor = Color(red = 35, green = 46, blue = 61)
    Box(
        Modifier
            .fillMaxSize()
            .background(customColor),
        contentAlignment = Alignment.Center
    ){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_animation))
        val isPlaying by remember {
            mutableStateOf(true)
        }
        var animationCounter by remember{
            mutableStateOf(0)
        }
        val progress by animateLottieCompositionAsState(composition = composition, isPlaying = isPlaying)

        LaunchedEffect(key1 = progress){
            if(progress == 1f){
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}