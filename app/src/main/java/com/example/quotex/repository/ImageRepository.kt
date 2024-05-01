package com.example.quotex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotex.BuildConfig
import com.example.quotex.api.ImageService
import com.example.quotex.models.ImageList
import java.util.Collections.shuffle

class ImageRepository (private val imageService: ImageService) {
    private val imagesLiveData  = MutableLiveData<ImageList>()
    private val apiKey = BuildConfig.IMAGE_API_KEY
    val images : LiveData<ImageList>
        get() = imagesLiveData

    suspend fun getImages(){
       val result = imageService.getImages("https://pixabay.com/api/?key=$apiKey&category=nature&per_page=200&safesearch=true")
        shuffle(result.hits)
       imagesLiveData.postValue(result)
    }
}
