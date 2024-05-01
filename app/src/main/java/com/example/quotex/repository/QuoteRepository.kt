package com.example.quotex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotex.api.QuoteService
import com.example.quotex.models.QuoteList
import java.util.Collections.shuffle

class QuoteRepository (private val quoteService: QuoteService) {
    private val quotesLiveData  = MutableLiveData<QuoteList>()

    val quotes : LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(){
        val result = quoteService.getQuotes("https://dummyjson.com/quotes?limit=500")
        shuffle(result.quotes)
        quotesLiveData.postValue(result)
    }
}
