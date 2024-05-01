package com.example.quotex.api

import com.example.quotex.models.QuoteList
import retrofit2.http.GET
import retrofit2.http.Url

interface QuoteService {
    @GET
    suspend fun getQuotes(@Url url : String): QuoteList
}
