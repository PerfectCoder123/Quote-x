package com.example.quotex.api

import com.example.quotex.models.ImageList
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageService {
    @GET
    suspend fun getImages(@Url url : String): ImageList
}