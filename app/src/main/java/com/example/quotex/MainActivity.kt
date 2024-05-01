package com.example.quotex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.imageLoader
import com.example.quotex.api.ImageService
import com.example.quotex.api.QuoteService
import com.example.quotex.api.RetrofitHelper
import com.example.quotex.components.HomeScreen
import com.example.quotex.components.SplashScreen
import com.example.quotex.models.ImageList
import com.example.quotex.models.QuoteList
import com.example.quotex.repository.ImageRepository
import com.example.quotex.repository.QuoteRepository
import com.example.quotex.ui.theme.QuoteXTheme
import com.example.quotex.utility.Screen
import com.example.quotex.viewmodels.ImageViewModel
import com.example.quotex.viewmodels.QuoteViewModel
import com.example.quotex.viewmodels.viewmodelfactory.ImageViewModelFactory
import com.example.quotex.viewmodels.viewmodelfactory.QuoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            QuoteXTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
                    val quoteRepository = QuoteRepository(quoteService)
                    val quoteViewModel = ViewModelProvider(this, QuoteViewModelFactory(quoteRepository))[QuoteViewModel::class.java]
                    val quotes by quoteViewModel.quotes.observeAsState()

                    val imageService = RetrofitHelper.getInstance().create(ImageService::class.java)
                    val imageRepository = ImageRepository(imageService)
                    val imageViewModel = ViewModelProvider(this, ImageViewModelFactory(imageRepository))[ImageViewModel::class.java]
                    val images by imageViewModel.images.observeAsState()

                    hideSystemUI()
                    App(imageLoader = imageLoader, quotes, images)
                }
            }
        }
    }
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
@Composable
fun App(imageLoader: ImageLoader, quotes: QuoteList?, images: ImageList?){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Splash.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, imageLoader, quotes,images)
        }
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
    }
}