package com.example.quotex.viewmodels.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotex.repository.ImageRepository
import com.example.quotex.repository.QuoteRepository
import com.example.quotex.viewmodels.ImageViewModel
import com.example.quotex.viewmodels.QuoteViewModel

@Suppress("UNCHECKED_CAST")
class ImageViewModelFactory (private val imageRepository: ImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(imageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
