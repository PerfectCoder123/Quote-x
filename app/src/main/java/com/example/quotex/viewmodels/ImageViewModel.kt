package com.example.quotex.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotex.models.ImageList
import com.example.quotex.repository.ImageRepository
import kotlinx.coroutines.launch

class ImageViewModel (private val repository: ImageRepository) : ViewModel() {

    init{
        viewModelScope.launch {
            repository.getImages()
        }
    }

    val images : LiveData<ImageList>
        get() = repository.images
}