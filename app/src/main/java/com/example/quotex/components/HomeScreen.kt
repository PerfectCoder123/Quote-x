package com.example.quotex.components

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.quotex.models.Hit
import com.example.quotex.models.ImageList
import com.example.quotex.models.Quote
import com.example.quotex.models.QuoteList
import com.example.quotex.ui.theme.vietnamPro


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController, imageLoader: ImageLoader, quotes: QuoteList?, images: ImageList?) {
    if(quotes != null && images != null){
        val pagerState = rememberPagerState(pageCount = {
            quotes.total.coerceAtMost(images.hits.size)
        })
        HorizontalPager(state = pagerState) { page ->
            QuoteWithImage(images.hits[page], quotes.quotes[page])
        }
    }
}


@Composable
fun QuoteWithImage(image: Hit, quote: Quote) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)){
        QuoteImage(imageUrl = image.largeImageURL)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            TopBar()
            QuoteContainer(quote)
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
fun QuoteImage(imageUrl : String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "background image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val activity = (LocalContext.current as? Activity)
    TopAppBar(
        title = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent), contentAlignment = Alignment.Center) {
                Text("Quote X", fontFamily = vietnamPro, fontSize = 25.sp)
            } },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = {
                activity?.finish()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            Spacer(modifier = Modifier.width(20.dp))
        }
    )
}

@Composable
fun QuoteContainer(quote : Quote) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .padding(10.dp, 0.dp)
        .verticalScroll(scrollState)
    ){
        Text(text = quote.quote,
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = vietnamPro,
                textAlign = TextAlign.Justify
            )
        )
        Text(text = "- ${quote.author}",
            modifier = Modifier
                .padding(0.dp, 20.dp)
                .align(Alignment.End),
            style = TextStyle(
                fontSize =18.sp,
                fontFamily = vietnamPro,
            )
        )
    }
}
